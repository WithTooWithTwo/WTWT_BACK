package wtwt.domain.auth.presentation.dto.response;

public record UserSummaryApiRes(
    Long id,
    String nickname,
    String profileImageUrl
) {

}
