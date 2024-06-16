package wtwt.common.doc.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import wtwt.common.dto.response.IdResponse;
import wtwt.common.dto.response.ScrollResponse;
import wtwt.domain.post.presentation.dto.PostSearch;
import wtwt.domain.post.presentation.dto.PostSummary;
import wtwt.domain.post.presentation.dto.request.CreatePostApiReq;
import wtwt.domain.post.presentation.dto.response.PostDetailResponse;

@Tag(name = "Post", description = "게시물 관련 API")
@SuppressWarnings("unused")
public interface PostSwagger {

    @Operation(summary = "임시저장 게시물 임시저장 (구현❌)", description = "임시저장된 게시물을 다시 임시저장할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "게시물 임시저장 성공",
            useReturnTypeSchema = true)
    })
    ResponseEntity<IdResponse> save(Long loginId, Long id, CreatePostApiReq request);

    @Operation(summary = "게시물 임시저장 (구현❌)", description = "게시물을 최초로 임시저장할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "게시물 임시저장 성공",
            useReturnTypeSchema = true)
    })
    ResponseEntity<IdResponse> createAndSave(Long loginId, CreatePostApiReq request);

    @Operation(summary = "임시저장 게시물 게시 (구현❌)", description = "임시저장된 게시물을 게시할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "게시물 게시 성공",
            useReturnTypeSchema = true)
    })
    ResponseEntity<IdResponse> publish(Long loginId, Long id, CreatePostApiReq request);

    @Operation(summary = "게시물 게시 (구현❌)", description = "임시저장이 아닌 게시물을 게시할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "게시물 게시 성공",
            useReturnTypeSchema = true)
    })
    ResponseEntity<IdResponse> createAndPublish(Long loginId, CreatePostApiReq request);

    @Operation(summary = "게시물 조회/검색", description = "게시물을 조회/검색 할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "게시물 조회/검색 성공",
            useReturnTypeSchema = true)
    })
    ResponseEntity<ScrollResponse<PostSummary>> getPosts(PostSearch request);

    @Operation(summary = "단일 게시물 조회", description = "특정 게시물을 조회 할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "게시물 조회 성공",
            useReturnTypeSchema = true)
    })
    ResponseEntity<PostDetailResponse> getPost();

}
