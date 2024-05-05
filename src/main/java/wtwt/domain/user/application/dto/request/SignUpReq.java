package wtwt.domain.user.application.dto.request;

import lombok.Builder;
import wtwt.common.util.ValidationUtils;

public record SignUpReq(
    String email,
    String password
) {

    @Builder
    public SignUpReq(String email, String password) {
        ValidationUtils.validateEmail(email, "이메일 형식이 올바르지 않습니다.");
        ValidationUtils.validatePassword(password, "비밀번호는 8자 이상, 영문, 숫자, 특수문자를 포함해야 합니다.");

        this.email = email;
        this.password = password;
    }
}
