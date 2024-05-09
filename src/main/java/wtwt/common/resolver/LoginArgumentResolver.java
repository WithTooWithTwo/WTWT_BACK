package wtwt.common.resolver;

import javax.security.sasl.AuthenticationException;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import wtwt.common.annotation.Login;

public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean hasLongType = parameter.getParameterType()
            .equals(Long.class);

        return hasLoginAnnotation && hasLongType;
    }

    @Override
    public Object resolveArgument(
        MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory
    ) throws AuthenticationException {
        Authentication authentication = SecurityContextHolder.getContext()
            .getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Long) {
            return authentication.getPrincipal(); // 사용자가 로그인한 경우, 로그인 ID 반환
        }

        throw new AuthenticationException("로그인이 필요합니다.");
    }

}

