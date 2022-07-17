package Main.repository;

import Main.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post getPostByTitle(String title);
}
