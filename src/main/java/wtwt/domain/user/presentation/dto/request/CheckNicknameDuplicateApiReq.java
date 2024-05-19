package wtwt.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CheckNicknameDuplicateApiReq(
    @NotBlank
    String nickname
) {

}
