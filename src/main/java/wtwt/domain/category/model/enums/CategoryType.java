package wtwt.domain.category.model.enums;

import java.util.Arrays;

public enum CategoryType {
    MAIN, SUB, SUB_SUB;

    public static CategoryType from(String type) {
        return Arrays.stream(CategoryType.values())
            .filter(categoryType -> categoryType.name().equals(type.toUpperCase()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리 타입입니다."));
    }

    public boolean isParentOf(CategoryType child) {
        if (this == MAIN) {
            return true;
        }
        return this == SUB && child == SUB_SUB;
    }
}
