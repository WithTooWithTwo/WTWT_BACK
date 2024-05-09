package wtwt.domain.auth.application.dto.response;

import lombok.Builder;
import wtwt.domain.user.model.User;

@Builder
public record UserSummary(
    Long id,
    String nickname,
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
