package wtwt.common.doc.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import wtwt.common.dto.response.IdResponse;
import wtwt.common.dto.response.ScrollResponse;
import wtwt.domain.auth.application.dto.response.UserSummary;
import wtwt.domain.user.presentation.UserSearch;
import wtwt.domain.user.presentation.dto.request.CheckEmailDuplicateApiReq;
import wtwt.domain.user.presentation.dto.request.CheckNicknameDuplicateApiReq;
import wtwt.domain.user.presentation.dto.request.SignUpApiReq;
import wtwt.domain.user.presentation.dto.request.UpdateBirthdateApiReq;
import wtwt.domain.user.presentation.dto.request.UpdateGenderApiReq;
import wtwt.domain.user.presentation.dto.request.UpdateProfileApiReq;
import wtwt.domain.user.presentation.dto.request.UpdateUserApiReq;
import wtwt.domain.user.presentation.dto.response.CheckDuplicateApiRes;

@Tag(name = "User", description = "회원 관련 API")
@SuppressWarnings("unused")
public interface UserSwagger {

    @Operation(summary = "회원 가입", description = "회원 가입을 할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "회원 가입 성공",
            useReturnTypeSchema = true)
    })
    ResponseEntity<IdResponse> signup(SignUpApiReq request);

    @Operation(summary = "이메일 중복 확인", description = "이메일 중복을 확인할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "중복 확인 성공",
            useReturnTypeSchema = true)
    })
    ResponseEntity<CheckDuplicateApiRes> checkEmailDuplicate(CheckEmailDuplicateApiReq request);

    @Operation(summary = "닉네임 중복 확인", description = "닉네임 중복을 확인할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "중복 확인 성공",
            useReturnTypeSchema = true)
    })
    ResponseEntity<CheckDuplicateApiRes> checkNicknameDuplicate(
        CheckNicknameDuplicateApiReq request);

    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "회원 정보 수정 성공",
            useReturnTypeSchema = true)
    })
    @Parameter(name = "id", description = "수정할 회원 식별자", example = "1", in = ParameterIn.PATH)
    ResponseEntity<IdResponse> updateUser(Long id, UpdateUserApiReq request);

    @Operation(summary = "회원 검색", description = "닉네임으로 회원을 검색할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "회원 검색 성공",
            useReturnTypeSchema = true)
    })
    ResponseEntity<ScrollResponse<UserSummary>> search(@ParameterObject UserSearch request);

    @Operation(summary = "프로필 수정", description = "프로필을 수정할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "프로필 수정 성공",
            useReturnTypeSchema = true)
    })
    @Parameter(name = "id", description = "수정할 회원 식별자", example = "1", in = ParameterIn.PATH)
    ResponseEntity<Void> updateProfile(Long id, UpdateProfileApiReq request);

    @Operation(summary = "생일 수정", description = "생일을 수정할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "생일 수정 성공",
            useReturnTypeSchema = true)
    })
    @Parameter(name = "id", description = "생일을 수정할 회원 식별자", example = "1", in = ParameterIn.PATH)
    public ResponseEntity<Void> updateBirthdate(Long id, UpdateBirthdateApiReq request);

    @Operation(summary = "성별 수정", description = "성별을 수정할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "성별 수정 성공",
            useReturnTypeSchema = true)
    })
    @Parameter(name = "id", description = "성별을 수정할 회원 식별자", example = "1", in = ParameterIn.PATH)
    ResponseEntity<Void> updateGender(Long id, UpdateGenderApiReq request);
}
