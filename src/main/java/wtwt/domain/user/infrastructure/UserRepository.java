package wtwt.domain.user.infrastructure;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import wtwt.domain.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Optional<User> findByEmail(String email);
}
