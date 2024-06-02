package wtwt.domain.user.presentation;

import static java.util.Objects.isNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 검색 요청")
public record UserSearch(
    @Schema(name = "keyword", description = "검색 키워드", example = "you")
    String keyword,
    @Schema(name = "cursor", description = "커서(사용자 ID), 이 커서의 다음 순서부터 조회", example = "0", defaultValue = "0")
    String cursor,
    @Schema(name = "size", description = "스크롤 할 데이터 크기", example = "10", defaultValue = "10")
    Integer size
) {

    public UserSearch(String keyword, String cursor, Integer size) {
        this.keyword = keyword;
        this.cursor = isNull(cursor) ? "0" : cursor;
        this.size = isNull(size) ? 10 : size;
    }
}
