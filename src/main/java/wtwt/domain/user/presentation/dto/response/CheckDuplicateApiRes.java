package wtwt.domain.user.presentation.dto.response;

import lombok.Builder;

@Builder
public record CheckDuplicateApiRes(
    boolean isDuplicated
) {

}
