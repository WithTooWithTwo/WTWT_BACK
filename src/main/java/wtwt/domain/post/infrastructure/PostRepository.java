package wtwt.domain.post.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wtwt.domain.post.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
