package wtwt.domain.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import wtwt.domain.category.model.Category;

@Builder
@Schema(description = "카테고리 요약 정보")
public record CategorySummary(
    @Schema(description = "카테고리 식별자", example = "1")
    Long id,
    @Schema(description = "카테고리 이름", example = "국내여행")
    String name
) {

    public static CategorySummary from(Category category) {
        return CategorySummary.builder()
            .id(category.getId())
            .name(category.getName())
            .build();
    }

}
