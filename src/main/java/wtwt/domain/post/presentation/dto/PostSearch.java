package wtwt.domain.post.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Schema(description = "게시물 검색 요청")
public record PostSearch(
    @Schema(description = "정렬 옵션(최신순/인기순/빠른순)", example = "RECENT|POPULAR|SOON")
    OrderOption order, //정렬 옵션 [RECENT, POPULAR, SOON]
    @Schema(description = "카테고리", example = "1")
    Long category, // 카테고리
    @Schema(description = "선호 성별(남성/여성/상관없음)", example = "MALE|FEMALE|NONE")
    String gender,
    @Schema(description = "최소 나이", example = "23")
    Integer minAge,
    @Schema(description = "최대 나이", example = "29")
    Integer maxAge,
    @Schema(description = "번개만남 여부", example = "false")
    Boolean isLightning,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "여행 시작 날짜", example = "2024-08-01")
    LocalDate startDate, //여행 시작 날짜
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "여행 종료 날짜", example = "2024-09-01")
    LocalDate endDate, //여행 종료 날짜
    @Schema(description = "최소 그룹 크기", example = "2")
    Integer minGroupSize,
    @Schema(description = "최대 그룹 크기", example = "6")
    Integer maxGroupSize,
    @Schema(description = "키워드", example = "애월")
    String keyword, // 키워드
    @Schema(description = "커서(해당 커서의 다음 데이터부터 조회)", example = "1", defaultValue = "0")
    String cursor, // 커서
    @Schema(description = "응답받을 데이터의 갯수", example = "10", defaultValue = "10")
    Integer size // 개수
) {

}
