package wtwt.domain.category.dto;

import static java.util.Objects.isNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import wtwt.domain.category.model.Category;
import wtwt.domain.category.model.enums.CategoryType;

@Builder
@Schema(description = "카테고리 상세 정보")
public record CategoryDetail(
    @Schema(description = "카테고리 식별자", example = "1")
    Long id,
    @Schema(description = "상위 카테고리 식별자", example = "1")
    Long parentId,
    @Schema(description = "카테고리 이름", example = "국내여행")
    String name,
    @Schema(description = "카테고리 타입", example = "MAIN")
    CategoryType type
) {

    public static CategoryDetail from(Category category) {
        return CategoryDetail.builder()
            .id(category.getId())
            .parentId(isNull(category.getParent()) ? null : category.getParent().getId())
            .name(category.getName())
            .type(category.getLevel())
            .build();
    }

}
