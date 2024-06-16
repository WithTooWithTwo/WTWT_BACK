package wtwt.domain.post.presentation.dto.request;

import static java.util.Objects.isNull;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.format.annotation.DateTimeFormat;
import wtwt.domain.draft.application.dto.request.SaveDraftReq;
import wtwt.domain.trip.model.enums.PreferGender;

@Schema(description = "게시물 임시저장 요청")
public record SaveDraftApiReq(
    @Schema(description = "임시저장 게시글 식별자", example = "1", nullable = true)
    Long draftId,
    @Schema(description = "카테고리 식별자", example = "3", nullable = true)
    Long categoryId,
    @Schema(description = "번개만남 여부", example = "true", nullable = true, defaultValue = "false")
    Boolean isLightning,
    @NotBlank
    @Size(max = 100)
    @Schema(description = "제목", example = "내일 애월 쪽에서 같이 노실 분 구해요!", maxLength = 100)
    String title,
    @Size(max = 3000)
    @Schema(description = "내용", example = "내일 오후 2시쯤부터 애월 쪽에서 같이 노실 분 구해요!", nullable = true, maxLength = 3000)
    String content,
    @Schema(description = "이미지 URL 리스트", example = "[\"https://wtwt-dev-bucket.s3.ap-northeast-2.amazonaws.com/public/image/6d348778-8487-4af9-9831-cbe805026c49.png\"]", nullable = true)
    List<String> urls,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    @Schema(description = "여행 시작 날짜", example = "2024-07-01", nullable = true)
    LocalDate startDate,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    @Schema(description = "여행 종료 날짜", example = "2024-07-01", nullable = true)
    LocalDate endDate,
    @Schema(description = "모집 인원", example = "4", nullable = true)
    Integer groupSize,
    @Schema(description = "참여자 식별자 리스트", example = "[1]", nullable = true)
    List<Long> members,
    @Schema(description = "선호 성별", example = "MALE|FEMALE|NONE", nullable = true)
    String preferGender,
    @PositiveOrZero
    @Schema(description = "최소 나이", example = "23", nullable = true)
    Integer preferMinAge,
    @Max(200)
    @Schema(description = "최대 나이", example = "29", nullable = true)
    Integer preferMaxAge,
    @Schema(description = "태그 리스트", example = "[\"애월\", \"루지\"]", nullable = true)
    List<String> tags
) {

    public SaveDraftReq toSaveDraftReq(Long loginId) {
        return SaveDraftReq.builder()
            .draftId(Optional.ofNullable(draftId()))
            .loginUserId(loginId)
            .categoryId(categoryId())
            .isLightning(isLightning())
            .title(title())
            .content(content())
            .urls(isNull(urls()) ? List.of() : urls())
            .startDate(startDate())
            .endDate(endDate())
            .groupSize(groupSize())
            .members(members())
            .preferGender(PreferGender.from(preferGender()))
            .preferMinAge(preferMinAge())
            .preferMaxAge(preferMaxAge())
            .tags(isNull(tags()) ? List.of() : tags())
            .build();
    }
}
