package Main.model;

import javax.persistence.*;

@Entity
@Table(name = "user_follow", schema = "connectville", catalog = "")
public class UserFollow {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "follow_id")
    private int followId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "group_id")
    private int groupId;

    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }

    public int getUserId() {
        return userId;
    }

    public UserFollow(int userId, int groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public UserFollow(){}

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserFollow that = (UserFollow) o;

        if (followId != that.followId) return false;
        if (userId != that.userId) return false;
        if (groupId != that.groupId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = followId;
        result = 31 * result + userId;
        result = 31 * result + groupId;
        return result;
    }
}
