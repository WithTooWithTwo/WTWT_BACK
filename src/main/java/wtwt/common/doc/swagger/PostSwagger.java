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

    @Operation(summary = "게시물 생성", description = "게시물을 생성 할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "게시물 생성 성공",
            useReturnTypeSchema = true)
    })
    ResponseEntity<IdResponse> create(Long loginId, CreatePostApiReq request);

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
