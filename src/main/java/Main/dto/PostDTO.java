package Main.dto;

import Main.model.Post;
import Main.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Integer userId;
    private String username;

    private int postId;
    private String title;
    private String topic;
    private String image;
    private String Link;
    private String text;
    private boolean isPinned;
    private int createUser;
    private Timestamp createTimestamp;

    public PostDTO(User user, Post post) {

        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.topic = post.getTopic();
        this.image = post.getImage();
        this.Link = post.getLink();
        this.text = post.getText();
        this.isPinned = post.isPinned();
        this.createUser = post.getCreateUser();
        this.createTimestamp = post.getCreateTimestamp();

    }
}
