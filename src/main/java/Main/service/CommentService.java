package Main.service;

import Main.dto.CommentDTO;
import Main.model.Post;
import Main.model.User;
import Main.model.UserComment;
import Main.repository.CommentRepository;
import Main.repository.PostRepository;
import Main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserComment addCommentToPost(UserComment userComment) {
        return commentRepository.save(userComment);
    }

    @RolesAllowed({"ADMIN_ROLE", "USER_CHECKED_ROLE"})
    public void deleteCommentFromPost(Integer userCommentId) {
        commentRepository.deleteById(userCommentId);
    }

    public List<CommentDTO> getCommentsFromPost(Integer postId) {
        List<UserComment> comments = commentRepository.getAllByPostId(postId);
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for(UserComment comment: comments){
            commentDTOs.add(new CommentDTO(comment, userRepository.findById(comment.getUserId()).get().getUsername()));
        }
        return commentDTOs;
    }
}