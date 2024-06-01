package wtwt.domain.category.model;

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
import wtwt.domain.category.model.enums.CategoryType;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

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
    @Column(name = "level", nullable = false, length = 10, columnDefinition = "VARCHAR(10)")
    private CategoryType level;

    @Builder
    private Category(String name, Category parent, CategoryType level) {
        this.name = name;
        this.parent = parent;
        this.level = level;
    }

    public static Category createMain(String name) {
        return Category.builder()
            .name(name)
            .level(CategoryType.MAIN)
            .build();
    }

    public static Category createSub(String name, Category parent) {
        return Category.builder()
            .name(name)
            .parent(parent)
            .level(CategoryType.SUB)
            .build();
    }

    public static Category createSubSub(String name, Category parent) {
        return Category.builder()
            .name(name)
            .parent(parent)
            .level(CategoryType.SUB_SUB)
            .build();
    }
}
