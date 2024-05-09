package wtwt.domain.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import wtwt.domain.auth.application.jwt.JwtManager;
import wtwt.exception.custom.TokenValidationFailException;

@Component
@RequiredArgsConstructor
public class JwtValidationFilter extends OncePerRequestFilter {

    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_PREFIX = "Bearer ";

    private final JwtManager jwtManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader(JWT_HEADER);
        String jwt = resolveToken(bearerToken);

        if (null != jwt) {
            try {
                Long userId = jwtManager.getUserId(jwt);
                Authentication auth = new UsernamePasswordAuthenticationToken(userId, null);

                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                throw new TokenValidationFailException("유효하지 않은 토큰입니다.");
            }
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JWT_PREFIX)) {
            return bearerToken.substring(JWT_PREFIX.length());
        }

        return null;
    }

}
