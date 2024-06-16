package wtwt.common.doc.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import wtwt.common.dto.response.IdResponse;
import wtwt.domain.draft.application.dto.response.DraftSummary;
import wtwt.domain.draft.model.DraftInfo;
import wtwt.domain.post.presentation.dto.request.SaveDraftApiReq;

@Tag(name = "Draft", description = "임시저장 게시물 관련 API")
@SuppressWarnings("unused")
public interface DraftSwagger {

    @Operation(summary = "게시물 임시저장", description = "게시물을 임시저장할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "게시물 임시저장 성공",
            useReturnTypeSchema = true)
    })
    ResponseEntity<IdResponse> save(Long loginId, SaveDraftApiReq request);

    @Operation(summary = "임시저장 게시물 전체 조회", description = "사용자의 임시저장 게시물들을 조회할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "임시저장 게시물 전체 조회 성공",
            useReturnTypeSchema = true)
    })
    @Parameter(name = "userId", description = "조회할 임시저장 게시물의 사용자 ID", example = "1", required = true, in = ParameterIn.QUERY)
    ResponseEntity<List<DraftSummary>> getAllByUser(Long loginId, Long userId);

    @Operation(summary = "임시저장 게시물 불러오기", description = "임시저장 게시물을 불러올(조회) 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "임시저장 게시물 불러오기 성공",
            useReturnTypeSchema = true)
    })
    @Parameter(name = "id", description = "임시저장 게시물 ID", example = "1", in = ParameterIn.PATH)
    ResponseEntity<DraftInfo> get(Long loginId, Long id);
}
