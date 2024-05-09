package wtwt.domain.auth.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wtwt.domain.auth.model.AuthProvider;
import wtwt.domain.auth.model.enums.ProviderType;
import wtwt.domain.user.model.User;

@Repository
public interface AuthProviderRepository extends JpaRepository<AuthProvider, Long> {

    boolean existsByUserAndProviderType(User user, ProviderType providerType);

    AuthProvider getByUserAndProviderType(User user, ProviderType providerType);

}
