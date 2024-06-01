package wtwt.domain.category.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtwt.domain.category.application.dto.request.CreateCategoryReq;
import wtwt.domain.category.dto.CategorySummary;
import wtwt.domain.category.infrastructure.CategoryRepository;
import wtwt.domain.category.model.Category;
import wtwt.domain.category.model.Category.CategoryBuilder;
import wtwt.domain.category.model.enums.CategoryType;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategorySummary create(CreateCategoryReq request) {
        CategoryBuilder categoryBuilder = Category.builder()
            .name(request.name())
            .level(request.type());

        request.parentId().ifPresent(parentId -> {
            Category parent = getParent(parentId, request.type());
            categoryBuilder.parent(parent);
        });

        return CategorySummary.from(categoryRepository.save(categoryBuilder.build()));
    }

    public List<CategorySummary> findMainCategories() {
        return findAllByType(CategoryType.MAIN);
    }

    public List<CategorySummary> findAllByParent(Long parentId) {
        Category parent = getCategory(parentId, "상위 카테고리가 존재하지 않습니다.");
        return categoryRepository.findAllByParent(parent).stream()
            .map(CategorySummary::from)
            .toList();
    }

    private Category getParent(Long parentId, CategoryType type) {
        Category parent = getCategory(parentId, "상위 카테고리가 존재하지 않습니다.");

        if (type.getParentType() != parent.getLevel()) {
            throw new IllegalArgumentException("상위 카테고리 타입이 올바르지 않습니다.");
        }

        return parent;
    }

    private List<CategorySummary> findAllByType(CategoryType type) {
        List<Category> categories = categoryRepository.findAllByLevel(type);
        return categories.stream()
            .map(CategorySummary::from)
            .toList();
    }

    private Category getCategory(Long id, String message) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(message));
    }
}
