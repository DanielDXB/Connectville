package Main.model;

import javax.persistence.*;

@Entity
@Table(name = "user_comment", schema = "connectville", catalog = "")
public class UserComment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "comment_id")
    private int commentId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "post_id")
    private int postId;
    @Basic
    @Column(name = "text")
    private String text;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserComment that = (UserComment) o;

        if (commentId != that.commentId) return false;
        if (userId != that.userId) return false;
        if (postId != that.postId) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + userId;
        result = 31 * result + postId;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
