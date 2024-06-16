package wtwt.domain.post.application.dto.request;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import wtwt.domain.trip.model.enums.PreferGender;

@Builder
public record CreatePostReq(
    Optional<Long> draftId,
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
    PreferGender preferGender,
    Integer preferMinAge,
    Integer preferMaxAge,
    List<String> tags
) {

}
