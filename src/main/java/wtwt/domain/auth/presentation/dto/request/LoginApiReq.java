package wtwt.domain.auth.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import wtwt.domain.auth.application.dto.requset.LoginReq;

@Schema(description = "로그인 요청")
public record LoginApiReq(
    @NotBlank
    @Schema(description = "이메일", example = "you_me@naver.com")
    String email,
    @NotBlank
    @Schema(description = "비밀번호", example = "you_me125!")
    String password
) {

    public LoginReq toLoginReq() {
        return LoginReq.builder()
            .email(email)
            .password(password)
            .build();
    }

}
