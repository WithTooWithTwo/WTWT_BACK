package wtwt.domain.user.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "이메일 중복 확인 요청")
public record CheckEmailDuplicateApiReq(
    @NotBlank
    @Email
    @Schema(description = "이메일", example = "you_me@naver.com")
    String email
) {

}
