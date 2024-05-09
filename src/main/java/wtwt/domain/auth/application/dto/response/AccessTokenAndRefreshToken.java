package wtwt.domain.auth.application.dto.response;

import lombok.Builder;

@Builder
public record AccessTokenAndRefreshToken(
    String accessToken,
    String refreshToken
) {

}
