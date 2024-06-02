package wtwt.domain.user.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "닉네임 중복 확인 요청")
public record CheckNicknameDuplicateApiReq(
    @NotBlank
    @Schema(description = "닉네임", example = "you_me")
    String nickname
) {

}
