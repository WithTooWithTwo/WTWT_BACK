package wtwt.common.doc.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import wtwt.domain.user.presentation.dto.request.CheckEmailDuplicateApiReq;
import wtwt.domain.user.presentation.dto.request.SignUpApiReq;
import wtwt.domain.user.presentation.dto.request.UpdateUserApiReq;
import wtwt.domain.user.presentation.dto.response.CheckDuplicateApiRes;

@Tag(name = "User", description = "회원 관련 API")
@SuppressWarnings("unused")
public interface UserSwagger {

    @Operation(summary = "회원 가입", description = "회원 가입을 할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "회원 가입 성공",
            content = @Content(schema = @Schema()))
    })
    ResponseEntity<Void> signup(SignUpApiReq request);

    @Operation(summary = "이메일 중복 확인", description = "이메일 중복을 확인할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "중복 확인 성공",
            content = @Content(schema = @Schema(implementation = CheckDuplicateApiRes.class)))
    })
    ResponseEntity<CheckDuplicateApiRes> checkEmailDuplicate(CheckEmailDuplicateApiReq request);

    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "회원 정보 수정 성공",
            content = @Content(schema = @Schema()))
    })
    @Parameter(name = "id", description = "수정할 회원 식별자", example = "1", in = ParameterIn.PATH)
    ResponseEntity<Void> updateUser(Long id, UpdateUserApiReq request);
}
