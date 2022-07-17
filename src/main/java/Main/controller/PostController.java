package Main.controller;

import Main.dto.PostDTO;
import Main.model.Post;
import Main.service.FileUploadService;
import Main.service.PostService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("post")
public class PostController {

    private final PostService postService;
    private final FileUploadService fileUploadService;
    @PostMapping("/new/{groupId}")
    public Integer addNewPost(@PathVariable Integer groupId, @RequestBody Post post) throws IOException {
        return postService.createPost(groupId, post).getPostId();
    }
    @PostMapping("/picture/{postId}")
    public void addPictureToPost(@RequestPart MultipartFile picture, @PathVariable Integer postId) throws IOException {
        postService.addPictureToPost(postId, picture);
    }



    @PostMapping("/picture")
    public void addPicture(@RequestPart Post post,
                           @RequestPart MultipartFile picture) throws IOException {
        System.out.println(picture.toString());
        System.out.println(post.getText());
        System.out.println(picture.getOriginalFilename());
        FileInputStream serviceAccount =
                new FileInputStream("connectville-88fb1-5de48fec5b73.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        StorageClient storageClient = StorageClient.getInstance(FirebaseApp.getInstance());
        String blobString = "postImages/" + post.getPostId() + ".jpg";
        storageClient.bucket("connectville-88fb1.appspot.com").create(blobString, picture.getInputStream(), Bucket.BlobWriteOption.userProject("connectville-88fb1"));
        System.out.println(FirebaseApp.getInstance().getName());

    }

    @GetMapping("/download")
    public void downloadFile() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("connectville-88fb1-5de48fec5b73.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        StorageClient storageClient = StorageClient.getInstance(FirebaseApp.getInstance());
        File file = new File("11.jpg");
        Blob pic = storageClient.bucket("connectville-88fb1.appspot.com").get("postImages/11.jpg");
        Path path = Paths.get("11.jpg");
        pic.downloadTo(path);
    }



    @PutMapping("/edit")
    @RolesAllowed({"ROLE_ADMIN"})
    public void editPost(@RequestBody Post post) {
        postService.editPost(post);
    }

    @GetMapping("/by-id")
    public PostDTO getPostById(@RequestParam Integer id) {
        return postService.getPostById(id);
    }

    @GetMapping("/all")
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/allPinned")
    public List<PostDTO> getAllPinnedPosts() {
        return postService.getAllPinnedPosts();
    }

    @GetMapping("/by-title")
    public PostDTO getPostByTitle(@RequestParam String title) {
        return postService.getPostByTitle(title);
    }

    @DeleteMapping("/delete/{postId}")
    //@RolesAllowed({"ROLE_ADMIN"})
    public void deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
    }
}
