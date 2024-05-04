package wtwt.domain.user.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CheckEmailDuplicateReq(
    @NotBlank
    @Email
    String email
) {

}
