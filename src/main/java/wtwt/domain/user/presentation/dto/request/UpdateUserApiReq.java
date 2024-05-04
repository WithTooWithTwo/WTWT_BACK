package wtwt.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import org.springframework.web.multipart.MultipartFile;
import wtwt.domain.user.model.enums.Gender;

public record UpdateUserApiReq(
    @NotBlank
    @Size(max = 10)
    String nickname,
    MultipartFile profileImage,
    @Size(max = 100)
    String statusMessage,
    @Past
    LocalDate birthDate,
    Gender gender
) {

}
