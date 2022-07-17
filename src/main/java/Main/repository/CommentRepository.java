package Main.repository;

import Main.model.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<UserComment, Integer> {
    List<UserComment> getAllByPostId(Integer postId);
}
