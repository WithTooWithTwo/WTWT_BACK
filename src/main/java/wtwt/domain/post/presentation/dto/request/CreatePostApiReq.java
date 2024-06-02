package wtwt.domain.post.presentation.dto.request;

import static java.util.Objects.isNull;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import wtwt.domain.post.application.dto.request.CreatePostReq;
import wtwt.domain.post.model.enums.PostStatus;
import wtwt.domain.user.model.enums.Gender;

public record CreatePostApiReq(
    @NotNull
    Long categoryId,
    Boolean isLightning,
    @NotBlank
    String title,
    @NotBlank
    String content,
    List<String> urls,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate,
    Integer groupSize,
    List<Long> members,
    String preferGender,
    Integer preferMinAge,
    Integer preferMaxAge,
    List<String> tags,
    @NotBlank
    String status
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
            .status(PostStatus.from(status()))
            .build();
    }
}
