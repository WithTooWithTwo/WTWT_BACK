package wtwt.domain.auth.application.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import wtwt.core.properties.JwtProperties;
import wtwt.domain.user.model.enums.Authority;
import wtwt.exception.custom.TokenValidationFailException;

@Component
public class JwtManager {

    public static final String USER_ID_CLAIM = "user_id";
    public static final String ROLES_CLAIM = "roles";
    private static final int MINUTE_PER_DAY = 24 * 60;
    private static final long MILLISECONDS_PER_MINUTE = 60 * 1000L;
    private final String issuer;
    private final SecretKey secretKey;
    private final long expiredAfter;
    private final long refreshExpiredAfter;

    public JwtManager(JwtProperties jwtProperties) {
        issuer = jwtProperties.issuer();
        secretKey = Keys.hmacShaKeyFor(jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8));
        expiredAfter = jwtProperties.expiredAfter() * MILLISECONDS_PER_MINUTE;
        refreshExpiredAfter =
            jwtProperties.refreshExpiredAfter() * MINUTE_PER_DAY * MILLISECONDS_PER_MINUTE;
    }

    public String generateAccessToken(long userId, List<Authority> roles) {
        return generateToken(userId, roles, expiredAfter);
    }

    public String generateRefreshToken(long userId, List<Authority> roles) {
        return generateToken(userId, roles, refreshExpiredAfter);
    }

    public Long getUserId(String token) {
        return parseClaims(token).get(USER_ID_CLAIM, Long.class);
    }

    public Long getExpiredAt(String token) {
        return parseClaims(token).getExpiration().getTime();
    }

    public Authority getRoles(String token) {
        return Authority.valueOf(parseClaims(token).get(ROLES_CLAIM, String.class));
    }

    public void validateToken(String refreshToken, Instant now) {
        Long expiredAt = getExpiredAt(refreshToken);
        if (now.toEpochMilli() > expiredAt) {
            throw new TokenValidationFailException("만료된 리프레시 토큰입니다.");
        }
    }

    private String generateToken(Long userId, List<Authority> roles, long expiredAfter) {
        Instant now = Instant.now();
        Instant expiredAt = now.plusMillis(expiredAfter);

        return Jwts.builder()
            .setIssuer(issuer)
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(expiredAt))
            .claim(USER_ID_CLAIM, userId)
            .claim(ROLES_CLAIM, roles)
            .signWith(secretKey)
            .compact();
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (ExpiredJwtException e) {
            throw new TokenValidationFailException("만료된 토큰입니다.");
        } catch (Exception e) {
            throw new TokenValidationFailException("유효하지 않은 토큰입니다.");
        }
    }
}
