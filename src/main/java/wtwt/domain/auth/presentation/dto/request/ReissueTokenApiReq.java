package wtwt.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import wtwt.domain.auth.application.dto.requset.ReissueTokenReq;

public record ReissueTokenApiReq(
    @NotBlank
    String refreshToken
) {

    public ReissueTokenReq toReissueTokenReq() {
        return ReissueTokenReq.builder()
            .refreshToken(refreshToken)
            .build();
    }

}
