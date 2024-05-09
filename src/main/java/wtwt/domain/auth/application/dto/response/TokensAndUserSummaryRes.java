package wtwt.domain.auth.application.dto.response;

import lombok.Builder;
import wtwt.domain.user.model.User;

@Builder
public record TokensAndUserSummaryRes(
    AccessTokenAndRefreshToken tokens,
    UserSummary user
) {

    public static TokensAndUserSummaryRes of(String accessToken, String refreshToken, User user) {
        return TokensAndUserSummaryRes.builder()
            .tokens(AccessTokenAndRefreshToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build())
            .user(UserSummary.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .build())
            .build();
    }

}
