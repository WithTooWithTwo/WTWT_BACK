package wtwt.common.doc.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import wtwt.domain.auth.presentation.dto.request.LoginApiReq;
import wtwt.domain.auth.presentation.dto.response.LoginApiRes;

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
}
