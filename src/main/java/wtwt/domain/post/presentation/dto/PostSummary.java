package wtwt.domain.post.presentation.dto;

import java.time.LocalDateTime;
import wtwt.domain.auth.application.dto.response.UserSummary;
import wtwt.domain.post.model.enums.PostStatus;

public record PostSummary(
    Long id,
    UserSummary user,
    LocalDateTime createdAt,
    String title,
    String content,
    Boolean isLightning,
    PostStatus status,
    Integer preferGroupSize,
    Integer groupSize,
    Integer hits,
    Boolean isLinked
) {

}
