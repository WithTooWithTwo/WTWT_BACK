package wtwt.domain.post.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

public record CreatePostApiReq(
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

}
