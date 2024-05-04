package wtwt.domain.user.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpReq(
    @NotBlank
    @Email
    String email,
    @NotBlank
    String password
) {

}
