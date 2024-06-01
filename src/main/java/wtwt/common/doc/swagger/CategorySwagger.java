package wtwt.common.doc.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import wtwt.common.dto.response.IdResponse;
import wtwt.domain.auth.presentation.dto.response.LoginApiRes;
import wtwt.domain.category.dto.CategorySummary;
import wtwt.domain.category.presentation.dto.request.CreateCategoryApiReq;

@Tag(name = "Category", description = "카테고리 관련 API")
public interface CategorySwagger {

    @Operation(summary = "카테고리 생성", description = "카테고리를 생성 할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "카테고리 생성 성공",
            content = @Content(schema = @Schema(implementation = LoginApiRes.class)))
    })
    ResponseEntity<IdResponse> create(CreateCategoryApiReq request);

    @Operation(summary = "메인 카테고리 조회", description = "메인 카테고리를 조회 할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "메인 카테고리 조회 성공",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = CategorySummary.class))
            ))
    })
    ResponseEntity<List<CategorySummary>> findMainCategories();

    @Operation(summary = "카테고리 조회", description = "부모 카테고리의 하위 카테고리를 조회 할 때 사용하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "카테고리 조회 성공",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = CategorySummary.class))
            ))
    })
    @Parameter(name = "parentId", description = "상위 카테고리 식별자", in = ParameterIn.QUERY)
    ResponseEntity<List<CategorySummary>> findAllByParent(Long parentId);
}
