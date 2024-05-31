package wtwt.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record UpdateProfileApiReq(
    @NotBlank
    @Size(max = 10)
    String nickname,
    @URL
    String profileImageUrl,
    @Size(max = 100)
    String statusMessage
) {

}
