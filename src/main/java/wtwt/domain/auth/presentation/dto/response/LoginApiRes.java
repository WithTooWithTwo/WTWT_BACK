package wtwt.domain.auth.presentation.dto.response;

public record LoginApiRes(
    String accessToken,
    String refreshToken,
    UserSummaryApiRes user
) {

}
