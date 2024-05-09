package wtwt.domain.auth.presentation.dto.response;

import lombok.Builder;
import wtwt.domain.auth.application.dto.response.TokensAndUserSummaryRes;

@Builder
public record LoginApiRes(
    String accessToken,
    String refreshToken,
    UserSummaryApiRes user
) {

    public static LoginApiRes from(TokensAndUserSummaryRes tokensAndUserSummaryRes) {
        return LoginApiRes.builder()
            .accessToken(tokensAndUserSummaryRes.tokens().accessToken())
            .refreshToken(tokensAndUserSummaryRes.tokens().refreshToken())
            .user(UserSummaryApiRes.from(tokensAndUserSummaryRes.user()))
            .build();
    }

}
