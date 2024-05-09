package wtwt.domain.auth.application;

import static wtwt.domain.auth.model.enums.ProviderType.BASIC;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtwt.domain.auth.application.dto.requset.LoginReq;
import wtwt.domain.auth.application.dto.response.TokensAndUserSummaryRes;
import wtwt.domain.auth.application.jwt.JwtManager;
import wtwt.domain.auth.infrastructure.AuthProviderRepository;
import wtwt.domain.auth.model.AuthProvider;
import wtwt.domain.user.infrastructure.UserRepository;
import wtwt.domain.user.model.User;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthProviderRepository authProviderRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtManager jwtManager;

    @Transactional
    public TokensAndUserSummaryRes login(LoginReq request) {
        User user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 사용자가 존재하지 않습니다."));

        if (!user.checkPassword(request.password(), passwordEncoder)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtManager.generateAccessToken(user.getId(),
            List.of(user.getAuthority()));
        String refreshToken = jwtManager.generateRefreshToken(user.getId(),
            List.of(user.getAuthority()));

        createAuthProviderIfNotExist(user);

        AuthProvider authProvider = authProviderRepository.getByUserAndProviderType(user, BASIC);
        authProvider.updateRefreshToken(refreshToken);

        return TokensAndUserSummaryRes.of(accessToken, refreshToken, user);
    }

    private void createAuthProviderIfNotExist(User user) {
        if (!authProviderRepository.existsByUserAndProviderType(user, BASIC)) {
            authProviderRepository.save(
                AuthProvider.builder()
                    .user(user)
                    .providerType(BASIC)
                    .build()
            );
        }
    }
}
