package wtwt.domain.auth.application;

import static wtwt.domain.auth.model.enums.ProviderType.BASIC;

import jakarta.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtwt.domain.auth.application.dto.requset.LoginReq;
import wtwt.domain.auth.application.dto.requset.ReissueTokenReq;
import wtwt.domain.auth.application.dto.response.AccessTokenAndRefreshToken;
import wtwt.domain.auth.application.dto.response.TokensAndUserSummaryRes;
import wtwt.domain.auth.application.dto.response.UserSummary;
import wtwt.domain.auth.application.jwt.JwtManager;
import wtwt.domain.auth.infrastructure.AuthProviderRepository;
import wtwt.domain.auth.model.AuthProvider;
import wtwt.domain.auth.model.enums.ProviderType;
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
    public TokensAndUserSummaryRes basicLogin(LoginReq request) {
        User user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 사용자가 존재하지 않습니다."));

        if (!user.checkPassword(request.password(), passwordEncoder)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        AuthProvider authProvider = createAuthProviderIfNotExist(user, BASIC);

        AccessTokenAndRefreshToken tokens = createTokens(user);
        authProvider.updateRefreshToken(tokens.refreshToken());

        return TokensAndUserSummaryRes.of(tokens, user);
    }

    @Transactional
    public TokensAndUserSummaryRes reissue(ReissueTokenReq request) {
        String refreshToken = request.refreshToken();
        Long userId = jwtManager.getUserId(refreshToken);
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
        AuthProvider authProvider = authProviderRepository
            .findByUserAndRefreshToken(user, refreshToken)
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 리프레시 토큰입니다."));

        jwtManager.validateToken(authProvider.getRefreshToken(), Instant.now());

        AccessTokenAndRefreshToken tokens = createTokens(user);
        authProvider.updateRefreshToken(tokens.refreshToken());

        return TokensAndUserSummaryRes.of(tokens, user);
    }

    private AccessTokenAndRefreshToken createTokens(User user) {
        String accessToken = jwtManager.generateAccessToken(user.getId(),
            List.of(user.getAuthority()));
        String refreshToken = jwtManager.generateRefreshToken(user.getId(),
            List.of(user.getAuthority()));

        return AccessTokenAndRefreshToken.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }

    private AuthProvider createAuthProviderIfNotExist(User user, ProviderType providerType) {
        if (!authProviderRepository.existsByUserAndProviderType(user, providerType)) {
            return authProviderRepository.save(
                AuthProvider.builder()
                    .user(user)
                    .providerType(BASIC)
                    .build()
            );
        }

        return authProviderRepository.getByUserAndProviderType(user, providerType);
    }

    public UserSummary loadUser(Long loginId) {
        User user = userRepository.findById(loginId)
            .orElseThrow(() -> new EntityNotFoundException("해당 사용자가 존재하지 않습니다."));

        return UserSummary.from(user);
    }
}
