package Main.controller;

import Main.dto.PostDTO;
import Main.model.Post;
import Main.service.FileUploadService;
import Main.service.PostService;
import Main.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("image")
@RequiredArgsConstructor
public class ImageController {
    private final FileUploadService fileUploadService;
    private final PostService postService;
    private final UserService userService;


    @GetMapping(
            value = "/get/{postId}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getPostImage(@PathVariable Integer postId, HttpServletResponse response) throws IOException {
        PostDTO post = postService.getPostById(postId);
        String filename  = "postImages/" + post.getPostId() + ".jpg";
        try{
            fileUploadService.downloadFile(filename).toByteArray();
        }
        catch (Exception e){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;

        }
        response.setStatus(HttpServletResponse.SC_OK);
        return fileUploadService.downloadFile(filename).toByteArray();

    }

    @GetMapping(
            value = "/getProfile/{userId}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getUserImage(@PathVariable Integer userId) throws IOException {
        PostDTO post = postService.getPostById(userId);
        String filename  = "userImage/" + post.getPostId() + ".jpg";
        return fileUploadService.downloadFile(filename).toByteArray();
    }

    @PostMapping("/picture/{userId}")
    public void addPictureToUser(@RequestPart MultipartFile picture, @PathVariable Integer userId) throws IOException {
        userService.addPictureToUser(userId, picture);
    }
}
