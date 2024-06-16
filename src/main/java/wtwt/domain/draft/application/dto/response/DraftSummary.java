package wtwt.domain.draft.application.dto.response;

import java.time.LocalDate;
import lombok.Builder;
import wtwt.domain.draft.model.Draft;

@Builder
public record DraftSummary(
    Long id,
    String title,
    LocalDate date
) {

    public static DraftSummary from(Draft draft) {
        return DraftSummary.builder()
            .id(draft.getId())
            .title(draft.getTitle())
            .date(draft.getUpdatedAt().toLocalDate())
            .build();
    }
}
