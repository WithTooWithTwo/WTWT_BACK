package wtwt.domain.post.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import wtwt.domain.auth.application.dto.response.UserSummary;
import wtwt.domain.post.model.enums.PostStatus;

@Schema(description = "게시물 요약")
public record PostSummary(
    @Schema(description = "게시물 식별자", example = "1")
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
