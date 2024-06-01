package wtwt.domain.category.presentation.dto.request;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import java.util.Optional;
import wtwt.domain.category.application.dto.request.CreateCategoryReq;
import wtwt.domain.category.model.enums.CategoryType;

public record CreateCategoryApiReq(
    @NotBlank
    @Parameter(
        name = "name",
        description = "카테고리 이름",
        required = true,
        example = "해외여행"
    )
    String name,
    @Parameter(
        name = "parentId",
        description = "상위 카테고리 타입",
        example = "1"
    )
    Long parentId,
    @NotBlank
    @Parameter(
        name = "type",
        description = "카테고리 타입(소문자 OK)",
        required = true,
        example = "MAIN | SUB | SUB_SUB"
    )
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
