package wtwt.common.doc.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import wtwt.domain.auth.presentation.dto.request.LoginApiReq;
import wtwt.domain.auth.presentation.dto.request.ReissueTokenApiReq;
import wtwt.domain.auth.presentation.dto.response.LoginApiRes;
import wtwt.domain.auth.presentation.dto.response.UserSummaryApiRes;

@Tag(name = "Auth", description = "인증/인가 관련 API")
public interface AuthSwagger {

    @Operation(summary = "기본(이메일/비밀번호) 로그인", description = "로그인 할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "로그인 성공",
            content = @Content(schema = @Schema(implementation = LoginApiRes.class))),
        @ApiResponse(responseCode = "302", description = "닉네임 설정 필요",
            content = @Content(schema = @Schema(implementation = LoginApiRes.class)))
    })
    ResponseEntity<LoginApiRes> login(LoginApiReq request);

    @Operation(summary = "Access / Refresh Token 재발급", description = "Refresh Token을 통해 토큰 재발급")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "토큰 재발급 성공",
            useReturnTypeSchema = true)
    })
    ResponseEntity<LoginApiRes> reissue(ReissueTokenApiReq request);

    @Operation(summary = "Access Token 검증", description = "Access Token을 통해 현재 로그인 된 사용자 프로필 요청")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "토큰 검증 성공",
            useReturnTypeSchema = true)
    })
    ResponseEntity<UserSummaryApiRes> validateToken(Long loginId);
}
