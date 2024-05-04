package wtwt.domain.user.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import wtwt.domain.user.application.dto.request.SignUpReq;

public record SignUpApiReq(
    @NotBlank
    @Email
    String email,
    @NotBlank
    String password
) {

    public SignUpReq toSignUpReq() {
        return SignUpReq.builder()
            .email(email)
            .password(password)
            .build();
    }

}
