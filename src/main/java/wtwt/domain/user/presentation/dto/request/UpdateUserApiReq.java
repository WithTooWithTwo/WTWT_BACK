package wtwt.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import org.hibernate.validator.constraints.URL;
import wtwt.domain.user.application.dto.request.UpdateUserReq;
import wtwt.domain.user.model.enums.Gender;

public record UpdateUserApiReq(
    @NotBlank
    @Size(max = 10)
    String nickname,
    @URL
    String profileImageUrl,
    @Size(max = 100)
    String statusMessage,
    @Past
    LocalDate birthDate,
    Gender gender
) {

    public UpdateUserReq toUpdateUserReq() {
        return UpdateUserReq.builder()
            .nickname(nickname)
            .profileImageUrl(profileImageUrl)
            .statusMessage(statusMessage)
            .birthDate(birthDate)
            .gender(gender)
            .build();
    }
}
