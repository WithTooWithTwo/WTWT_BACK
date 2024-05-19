package wtwt.domain.user.application.dto.request;

import java.time.LocalDate;
import lombok.Builder;
import wtwt.domain.user.model.enums.Gender;

@Builder
public record UpdateUserReq(
    String nickname,
    String profileImageUrl,
    String statusMessage,
    LocalDate birthDate,
    Gender gender
) {

}
