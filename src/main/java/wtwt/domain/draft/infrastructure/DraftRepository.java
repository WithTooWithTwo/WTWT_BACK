package wtwt.domain.draft.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wtwt.domain.draft.model.Draft;
import wtwt.domain.user.model.User;

public interface DraftRepository extends JpaRepository<Draft, Long> {

    List<Draft> findByWriter(User writer);
}
