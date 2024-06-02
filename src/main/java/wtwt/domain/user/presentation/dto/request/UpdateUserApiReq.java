package wtwt.domain.user.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import org.hibernate.validator.constraints.URL;
import wtwt.domain.user.application.dto.request.UpdateUserReq;
import wtwt.domain.user.model.enums.Gender;

@Schema(description = "사용자 정보 수정 요청")
public record UpdateUserApiReq(
    @NotBlank
    @Size(max = 10)
    @Schema(description = "닉네임", example = "you_me")
    String nickname,
    @URL
    @Schema(description = "프로필 이미지 URL", example = "https://wtwt-dev-bucket.s3.ap-northeast-2.amazonaws.com/public/image/6d348778-8487-4af9-9831-cbe805026c49.png")
    String profileImageUrl,
    @Size(max = 100)
    @Schema(description = "상태 메시지", example = "you_me in Paris🥐")
    String statusMessage,
    @Past
    @Schema(description = "생년월일", example = "2000-01-01")
    LocalDate birthDate,
    @Schema(description = "성별", example = "MALE|FEMALE|HIDE")
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
