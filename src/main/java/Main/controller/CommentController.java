package Main.controller;

import Main.dto.CommentDTO;
import Main.model.UserComment;
import Main.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/new-comment")
    public UserComment createComment(@RequestBody UserComment userComment) {
        return commentService.addCommentToPost(userComment);
    }

    @DeleteMapping("/delete-comment/{commentId}")
    public void deleteComment(@PathVariable Integer commentId) {
        commentService.deleteCommentFromPost(commentId);
    }

    @GetMapping("/all-comments-from-post/{postId}")
    public List<CommentDTO> getCommentsFromPost(@PathVariable Integer postId) {
        List<CommentDTO> comments =  commentService.getCommentsFromPost(postId);
        for(CommentDTO comment: comments){
            System.out.println(comment.toString());
        }
        return comments;
    }
}
