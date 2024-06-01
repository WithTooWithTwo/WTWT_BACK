package wtwt.domain.user.presentation;

import static java.util.Objects.isNull;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

public record UserSearch(
    @Parameter(
        name = "keyword",
        description = "검색 키워드",
        example = "유나",
        in = ParameterIn.QUERY
    )
    String keyword,
    @Parameter(
        name = "cursor",
        description = "커서(사용자 ID), 이 커서의 다음 순서부터 조회",
        example = "0 (default)",
        in = ParameterIn.QUERY
    )
    String cursor,
    @Parameter(
        name = "size",
        description = "스크롤 할 데이터 크기",
        example = "10 (default)",
        in = ParameterIn.QUERY
    )
    Integer size
) {

    public UserSearch(String keyword, String cursor, Integer size) {
        this.keyword = keyword;
        this.cursor = isNull(cursor) ? "0" : cursor;
        this.size = isNull(size) ? 10 : size;
    }
}
