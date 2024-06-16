package wtwt.domain.post.presentation.dto.request;

import static java.util.Objects.isNull;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import wtwt.domain.post.application.dto.request.CreatePostReq;
import wtwt.domain.post.model.enums.PostStatus;
import wtwt.domain.user.model.enums.Gender;

@Schema(description = "게시물 생성 요청")
public record CreatePostApiReq(
    @NotNull
    @Schema(description = "카테고리 식별자", example = "3")
    Long categoryId,
    @Schema(description = "번개만남 여부", example = "true", nullable = true, defaultValue = "false")
    Boolean isLightning,
    @NotBlank
    @Size(max = 100)
    @Schema(description = "제목", example = "내일 애월 쪽에서 같이 노실 분 구해요!", maxLength = 100)
    String title,
    @NotBlank
    @Size(max = 3000)
    @Schema(description = "내용", example = "내일 오후 2시쯤부터 애월 쪽에서 같이 노실 분 구해요!", maxLength = 3000)
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
    @Schema(description = "참여자 식별자 리스트", example = "[1, 2, 3]", nullable = true)
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

    public CreatePostReq toCreatePostReq(Long loginId) {
        return CreatePostReq.builder()
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
            .preferGender(Gender.from(preferGender()))
            .preferMinAge(preferMinAge())
            .preferMaxAge(preferMaxAge())
            .tags(isNull(tags()) ? List.of() : tags())
            .build();
    }
}
