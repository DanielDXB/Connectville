package Main.repository;

import Main.model.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow, Integer> {

        List<UserFollow> findByUserId(Integer userId);
        int deleteByUserIdAndGroupId(Integer userId, Integer groupId);

        UserFollow findByUserIdAndGroupId(Integer userId, Integer groupId);
}
