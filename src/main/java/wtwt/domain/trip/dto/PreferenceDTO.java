package wtwt.domain.trip.dto;

import wtwt.domain.user.model.enums.Gender;

public record PreferenceDTO(
    Gender gender,
    Integer minAge,
    Integer maxAge,
    Integer groupSize
) {

}
