package wtwt.domain.auth.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import wtwt.domain.auth.application.dto.requset.ReissueTokenReq;

@Schema(description = "토큰 재발급 요청")
public record ReissueTokenApiReq(
    @NotBlank
    @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjdXBpdG9vIiwiaWF0IjoxNzE3MzE4MzUzLCJleHAiOjE3MTc5MjMxNTMsInVzZXJfaWQiOjEsInJvbGVzIjpbIk5PUk1BTCJdfQ.VqYVLGHokdXs68vrQhtMr2PDLgCgtsbKklWEcplvMpiglrb0y15T3k1uYBCUzMA2j_N9cMQnWAjVf1I9zD4yeQ")
    String refreshToken
) {

    public ReissueTokenReq toReissueTokenReq() {
        return ReissueTokenReq.builder()
            .refreshToken(refreshToken)
            .build();
    }

}
