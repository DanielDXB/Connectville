package Main.dto;

import Main.model.UserComment;
import lombok.Data;

import javax.persistence.*;

@Data
public class CommentDTO {
    private int commentId;
    private int userId;
    private String username;
    private int postId;
    private String text;

    public CommentDTO(UserComment userComment, String username) {
        this.commentId = userComment.getCommentId();
        this.userId = userComment.getUserId();
        this.username = username;
        this.postId = userComment.getPostId();
        this.text = userComment.getText();
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", postId=" + postId +
                ", text='" + text + '\'' +
                '}';
    }
}
