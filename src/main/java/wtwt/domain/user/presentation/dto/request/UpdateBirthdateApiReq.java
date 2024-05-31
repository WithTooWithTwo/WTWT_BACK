package wtwt.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

public record UpdateBirthdateApiReq(
    @NotNull
    @Past
    LocalDate birthDate
) {

}
