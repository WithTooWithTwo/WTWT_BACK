package wtwt.domain.post.presentation.dto;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public record PostSearch(
    OrderOption order, //정렬 옵션 [RECENT, POPULAR, SOON]
    Long category, // 카테고리
    String gender,
    Integer minAge,
    Integer maxAge,
    Boolean isLightning,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate, //여행 시작 날짜
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate, //여행 종료 날짜
    Integer minGroupSize,
    Integer maxGroupSize,
    String keyword, // 키워드
    String cursor, // 커서
    Integer size // 개수
) {

}
