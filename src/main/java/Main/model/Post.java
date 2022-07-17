package Main.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "posts", schema = "connectville", catalog = "")
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "post_id")
    private int postId;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "topic")
    private String topic;
    @Basic
    @Column(name = "image")
    private String image;
    @Basic
    @Column(name = "link")
    private String link;
    @Basic
    @Column(name = "text")
    private String text;
    @Basic
    @Column(name = "pinned")
    private boolean isPinned;
    @Basic
    @Column(name = "create_user")
    private int createUser;
    @Basic
    @Column(name = "create_timestamp")
    private Timestamp createTimestamp;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isPinned() {
        return isPinned;
    }


    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post that = (Post) o;

        if (postId != that.postId) return false;
        if (createUser != that.createUser) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (createTimestamp != null ? !createTimestamp.equals(that.createTimestamp) : that.createTimestamp != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = postId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + createUser;
        result = 31 * result + (createTimestamp != null ? createTimestamp.hashCode() : 0);
        return result;
    }
}
