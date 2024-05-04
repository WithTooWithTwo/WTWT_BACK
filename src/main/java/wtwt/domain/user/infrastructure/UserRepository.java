package wtwt.domain.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import wtwt.domain.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
