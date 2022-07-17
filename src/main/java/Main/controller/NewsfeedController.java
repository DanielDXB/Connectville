package Main.controller;

import Main.dto.PostDTO;
import Main.model.Post;
import Main.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/newsfeed")
public class NewsfeedController {
    private final PostService postService;

    @GetMapping("/")
    public List<PostDTO> getNewsfeed() {
        //all posts
        List<PostDTO> posts = postService.getPostByGroup(4);
        List<PostDTO> postsToReturn = new ArrayList<>();
        postsToReturn.add(posts.get(0));
        postsToReturn.add(posts.get(1));
        postsToReturn.add(posts.get(2));
        postsToReturn.add(posts.get(3));
        return postsToReturn;
    }
    @GetMapping("/all")
    public List<PostDTO> getNewsfeedAll() {
        //all posts
        List<PostDTO> posts = postService.getPostByGroup(4);
        return posts;
    }

    @RolesAllowed({"ADMIN"})
    @PostMapping("/pin/{postId}")
    public Post pinPost(@PathVariable Integer postId){
        return postService.pinPost(postId);
    }
}
