package Main.model;

import javax.persistence.*;

@Entity
@Table(name = "group_posts", schema = "connectville", catalog = "")
public class GroupPost {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "group_id")
    private int groupId;
    @Basic
    @Column(name = "post_id")
    private int postId;

    public GroupPost(int groupId, int postId) {
        this.groupId = groupId;
        this.postId = postId;
    }

    public GroupPost(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupPost that = (GroupPost) o;

        if (id != that.id) return false;
        if (groupId != that.groupId) return false;
        if (postId != that.postId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + groupId;
        result = 31 * result + postId;
        return result;
    }
}
