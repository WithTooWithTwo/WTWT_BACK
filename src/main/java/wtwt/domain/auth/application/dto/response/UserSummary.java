package wtwt.domain.auth.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import wtwt.domain.user.model.User;

@Builder
@Schema(description = "사용자 요약 정보")
public record UserSummary(
    @Schema(description = "사용자 식별자", example = "1")
    Long id,
    @Schema(description = "닉네임", example = "you_me")
    String nickname,
    @Schema(description = "프로필 이미지 URL", example = "https://wtwt-dev-bucket.s3.ap-northeast-2.amazonaws.com/public/image/6d348778-8487-4af9-9831-cbe805026c49.png")
    String profileImageUrl
) {

    public static UserSummary from(User user) {
        return UserSummary.builder()
            .id(user.getId())
            .nickname(user.getNickname())
            .profileImageUrl(user.getProfileImageUrl())
            .build();
    }

}
