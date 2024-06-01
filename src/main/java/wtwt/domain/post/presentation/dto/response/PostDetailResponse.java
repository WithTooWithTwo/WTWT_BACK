package wtwt.domain.post.presentation.dto.response;

import java.time.LocalDate;
import java.util.List;
import wtwt.domain.auth.application.dto.response.UserSummary;
import wtwt.domain.category.dto.CategoryDTO;
import wtwt.domain.post.model.enums.PostStatus;
import wtwt.domain.trip.dto.PreferenceDTO;

public record PostDetailResponse(
    Long id,
    String title,
    Integer hits,
    Integer countOfLikes,
    UserSummary writer,
    Boolean isLightning,
    LocalDate startDate,
    LocalDate endDate,
    List<String> tags,
    List<UserSummary> members,
    PreferenceDTO preference,
    String content,
    List<String> images,
    CategoryDTO category,
    PostStatus status,
    Boolean isLiked
) {

}
