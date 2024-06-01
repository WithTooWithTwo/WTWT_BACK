package wtwt.domain.category.dto;

import static java.util.Objects.isNull;

import lombok.Builder;
import wtwt.domain.category.model.Category;
import wtwt.domain.category.model.enums.CategoryType;

@Builder
public record CategoryDetail(
    Long id,
    Long parentId,
    String name,
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
