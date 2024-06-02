package wtwt.domain.user.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import wtwt.domain.user.application.dto.request.SignUpReq;

@Schema(description = "회원가입 요청")
public record SignUpApiReq(
    @NotBlank
    @Email
    @Schema(description = "이메일", example = "you_me@naver.com")
    String email,
    @NotBlank
    @Schema(description = "비밀번호", example = "you_me125!")
    String password
) {

    public SignUpReq toSignUpReq() {
        return SignUpReq.builder()
            .email(email)
            .password(password)
            .build();
    }

}
