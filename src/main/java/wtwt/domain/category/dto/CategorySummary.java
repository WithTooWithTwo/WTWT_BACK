package wtwt.domain.category.dto;

import lombok.Builder;
import wtwt.domain.category.model.Category;

@Builder
public record CategorySummary(
    Long id,
    String name
) {

    public static CategorySummary from(Category category) {
        return CategorySummary.builder()
            .id(category.getId())
            .name(category.getName())
            .build();
    }

}
