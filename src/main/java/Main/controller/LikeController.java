package Main.controller;

import Main.model.UserLike;
import Main.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/add")
    public UserLike createLike(UserLike userLike) {
        return likeService.createLike(userLike);
    }

    @RolesAllowed({"USER_ADMIN"})
    @DeleteMapping("/remove/{likeId}")
    public void removeLike(@PathVariable Integer likeId) {
        likeService.removeLike(likeId);
    }

    @GetMapping("/all-from-post/{postId}")
    public List<UserLike> getAllLikesByPostId(@PathVariable Integer postId) {
        return likeService.getAllLikesByPostId(postId);
    }

    @GetMapping("/all-from-user/{userId}")
    public List<UserLike> getAllLikesByUserId(@PathVariable Integer userId) {
        return likeService.getAllLikesByPostId(userId);
    }

    @GetMapping("/liked/{postId}/{userId}")
    public Boolean isLiked(@PathVariable Integer postId, @PathVariable Integer userId) {
        return likeService.isLiked(postId, userId);
    }

    @PostMapping("/like/{postId}/{userId}")
    public void likePost(@PathVariable Integer postId, @PathVariable Integer userId) {
        likeService.likePost(postId, userId);
    }
}
