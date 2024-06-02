package wtwt.domain.post.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import wtwt.domain.auth.application.dto.response.UserSummary;
import wtwt.domain.category.dto.CategoryDetail;
import wtwt.domain.post.model.enums.PostStatus;
import wtwt.domain.trip.dto.PreferenceDTO;

@Schema(description = "게시물 상세 정보")
public record PostDetailResponse(
    @Schema(description = "게시물 식별자", example = "1")
    Long id,
    @Schema(description = "제목", example = "내일 2시에 제주도 여행 같이 가실 분 구합니다.")
    String title,
    @Schema(description = "조회수", example = "10")
    Integer hits,
    @Schema(description = "좋아요 수", example = "5")
    Integer countOfLikes,
    @Schema(description = "작성자")
    UserSummary writer,
    @Schema(description = "번개만남 여부", example = "true")
    Boolean isLightning,
    @Schema(description = "여행 시작 날짜", example = "2024-08-01")
    LocalDate startDate,
    @Schema(description = "여행 종료 날짜", example = "2024-08-01")
    LocalDate endDate,
    @Schema(description = "태그 리스트", example = "[\"제주도\", \"애월\", \"내일\"]")
    List<String> tags,
    @Schema(description = "동행자 리스트", example = "[]")
    List<UserSummary> members,
    PreferenceDTO preference,
    @Schema(description = "내용", example = "내일 2시에 제주도 여행 같이 가실 분 구합니다.")
    String content,
    @Schema(description = "이미지 리스트", example = "[]")
    List<String> images,
    CategoryDetail category,
    @Schema(description = "게시물 상태", example = "PUBLISHED")
    PostStatus status,
    @Schema(description = "본인이 좋아요를 눌렀는지 여부", example = "true")
    Boolean isLiked
) {

}
