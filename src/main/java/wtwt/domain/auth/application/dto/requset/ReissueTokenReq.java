package wtwt.domain.auth.application.dto.requset;

import lombok.Builder;

@Builder
public record ReissueTokenReq(
    String refreshToken
) {

}
