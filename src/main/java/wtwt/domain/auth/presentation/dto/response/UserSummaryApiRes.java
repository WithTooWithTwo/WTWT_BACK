package wtwt.domain.auth.presentation.dto.response;

import lombok.Builder;
import wtwt.domain.auth.application.dto.response.UserSummary;

@Builder
public record UserSummaryApiRes(
    Long id,
    String nickname,
    String profileImageUrl
) {

    public static UserSummaryApiRes from(UserSummary user) {
        return UserSummaryApiRes.builder()
            .id(user.id())
            .nickname(user.nickname())
            .profileImageUrl(user.profileImageUrl())
            .build();
    }
}
