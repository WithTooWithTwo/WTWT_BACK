package wtwt.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import wtwt.domain.user.model.enums.Gender;

public record UpdateGenderApiReq(
    @NotNull
    Gender gender
) {

}
