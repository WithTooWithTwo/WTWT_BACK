package wtwt.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginApiReq(
    @NotBlank
    String email,
    @NotBlank
    String password
) {

}
