package wtwt.domain.category.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Optional;
import wtwt.domain.category.application.dto.request.CreateCategoryReq;
import wtwt.domain.category.model.enums.CategoryType;

@Schema(description = "카테고리 생성 요청")
public record CreateCategoryApiReq(
    @NotBlank
    @Size(max = 50)
    @Schema(name = "name", description = "카테고리 이름 (최대 50자)", example = "해외여행")
    String name,
    @Schema(name = "parentId", description = "상위 카테고리 타입", example = "1", nullable = true)
    Long parentId,
    @NotBlank
    @Schema(name = "type", description = "카테고리 타입(소문자 OK)", example = "MAIN|SUB|SUB_SUB")
    String type
) {

    public CreateCategoryReq toCreateCategoryReq() {
        return CreateCategoryReq.builder()
            .name(name)
            .parentId(Optional.ofNullable(parentId))
            .type(CategoryType.from(type))
            .build();
    }

}
