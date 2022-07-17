package Main.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_like", schema = "connectville", catalog = "")
@Data
public class UserLike {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "like_id")
    private Integer likeId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "post_id")
    private int postId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLike that = (UserLike) o;

        if (likeId != that.likeId) return false;
        if (userId != that.userId) return false;
        if (postId != that.postId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = likeId;
        result = 31 * result + userId;
        result = 31 * result + postId;
        return result;
    }


}
