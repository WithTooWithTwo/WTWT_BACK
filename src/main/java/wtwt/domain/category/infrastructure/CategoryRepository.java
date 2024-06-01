package wtwt.domain.category.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wtwt.domain.category.model.Category;
import wtwt.domain.category.model.enums.CategoryType;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByLevel(CategoryType type);

    List<Category> findAllByParent(Category parent);
}
