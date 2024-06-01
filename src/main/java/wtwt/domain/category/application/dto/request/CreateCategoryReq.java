package wtwt.domain.category.application.dto.request;

import java.util.Optional;
import lombok.Builder;
import wtwt.domain.category.model.enums.CategoryType;

@Builder
public record CreateCategoryReq(
    String name,
    Optional<Long> parentId,
    CategoryType type
) {

}
