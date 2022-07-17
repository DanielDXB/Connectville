package Main.repository;

import Main.model.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<UserLike, Integer> {

    List<UserLike> getAllByPostId(Integer postId);
    List<UserLike> getAllByUserId(Integer userId);
    UserLike findByPostIdAndUserId(Integer userId, Integer postId);
    void deleteByLikeId(Integer likeId);
}
