package wtwt.domain.user.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "중복 확인 응답")
public record CheckDuplicateApiRes(
    @Schema(description = "중복 여부", example = "false")
    boolean isDuplicated
) {

}
