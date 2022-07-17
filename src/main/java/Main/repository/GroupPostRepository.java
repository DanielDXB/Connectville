package Main.repository;

import Main.model.GroupPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupPostRepository extends JpaRepository<GroupPost, Integer> {

    int deleteByGroupIdAndPostId(Integer groupId, Integer postId);
    int deleteByPostId(Integer postId);
    List<GroupPost> findGroupPostByGroupId(Integer groupId);


    Optional<GroupPost> findGroupPostByPostId(Integer postId);
}