package wtwt.domain.auth.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import wtwt.domain.auth.application.dto.response.TokensAndUserSummaryRes;

@Builder
@Schema(description = "로그인 응답")
public record LoginApiRes(
    @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjdXBpdG9vIiwiaWF0IjoxNzE3MzE4MzUzLCJleHAiOjE3MTczMjE5NTMsInVzZXJfaWQiOjEsInJvbGVzIjpbIk5PUk1BTCJdfQ.EYSF8MVyfeyVu0JyaaAGreMw3uorVd0WuOO4uE6DCv5O3j9BmRhzviETkKSzykc9MQ_64Hc6IHWe-xwHJY-HGA")
    String accessToken,
    @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjdXBpdG9vIiwiaWF0IjoxNzE3MzE4MzUzLCJleHAiOjE3MTc5MjMxNTMsInVzZXJfaWQiOjEsInJvbGVzIjpbIk5PUk1BTCJdfQ.VqYVLGHokdXs68vrQhtMr2PDLgCgtsbKklWEcplvMpiglrb0y15T3k1uYBCUzMA2j_N9cMQnWAjVf1I9zD4yeQ")
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
