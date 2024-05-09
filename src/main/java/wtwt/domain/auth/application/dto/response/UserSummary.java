package wtwt.domain.auth.application.dto.response;

import lombok.Builder;

@Builder
public record UserSummary(
    Long id,
    String nickname,
    String profileImageUrl
) {

}
