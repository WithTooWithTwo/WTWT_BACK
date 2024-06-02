package wtwt.domain.post.application.dto.request;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import wtwt.domain.post.model.enums.PostStatus;
import wtwt.domain.user.model.enums.Gender;

@Builder
public record CreatePostReq(
    Long loginUserId,
    Long categoryId,
    Boolean isLightning,
    String title,
    String content,
    List<String> urls,
    LocalDate startDate,
    LocalDate endDate,
    Integer groupSize,
    List<Long> members,
    Gender preferGender,
    Integer preferMinAge,
    Integer preferMaxAge,
    List<String> tags,
    PostStatus status
) {

}
