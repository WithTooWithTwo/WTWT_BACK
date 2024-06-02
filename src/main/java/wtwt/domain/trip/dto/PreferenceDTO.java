package wtwt.domain.trip.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import wtwt.domain.user.model.enums.Gender;

@Schema(description = "선호 정보")
public record PreferenceDTO(
    @Schema(description = "선호 성별", example = "MALE|FEMALE|NONE")
    Gender gender,
    @Schema(description = "선호 최소 나이", example = "23")
    Integer minAge,
    @Schema(description = "선호 최대 나이", example = "29")
    Integer maxAge,
    @Schema(description = "선호 동행자 수", example = "4")
    Integer groupSize
) {

}
