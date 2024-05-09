package wtwt.domain.auth.application.dto.response;

import lombok.Builder;
import wtwt.domain.user.model.User;

@Builder
public record TokensAndUserSummaryRes(
    AccessTokenAndRefreshToken tokens,
    UserSummary user
) {

    public static TokensAndUserSummaryRes of(AccessTokenAndRefreshToken tokens, User user) {
        return TokensAndUserSummaryRes.builder()
            .tokens(tokens)
            .user(UserSummary.from(user))
            .build();
    }

}
