package wtwt.domain.category.model;

import static wtwt.common.util.ValidationUtils.validateNotBlank;
import static wtwt.common.util.ValidationUtils.validateNotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wtwt.common.base.BaseTimeEntity;
import wtwt.domain.category.model.enums.CategoryType;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false, length = 20, columnDefinition = "VARCHAR(20)")
    private CategoryType level;

    @Builder
    private Category(String name, Category parent, CategoryType level) {
        validateNotBlank(name, "카테고리명은 필수입니다.");
        validateNotNull(level, "카테고리 레벨은 필수입니다.");
        validateParent(parent.level, level);

        this.name = name;
        this.parent = parent;
        this.level = level;
    }

    private void validateParent(CategoryType parent, CategoryType level) {
        if (!parent.isParentOf(level)) {
            validateNotNull(parent, "올바르지 않은 계층 구조입니다.");
        }
    }
}
