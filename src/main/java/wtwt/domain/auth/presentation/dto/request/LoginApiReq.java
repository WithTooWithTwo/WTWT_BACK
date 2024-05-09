package wtwt.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import wtwt.domain.auth.application.dto.requset.LoginReq;

public record LoginApiReq(
    @NotBlank
    String email,
    @NotBlank
    String password
) {

    public LoginReq toLoginReq() {
        return LoginReq.builder()
            .email(email)
            .password(password)
            .build();
    }

}
