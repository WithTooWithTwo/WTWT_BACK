package wtwt.domain.draft.model;

import static java.util.Objects.isNull;

import java.time.LocalDate;
import java.util.List;
import wtwt.domain.auth.application.dto.response.UserSummary;
import wtwt.domain.category.dto.CategorySummary;
import wtwt.domain.category.model.Category;
import wtwt.domain.draft.application.dto.request.SaveDraftReq;
import wtwt.domain.trip.model.enums.PreferGender;
import wtwt.domain.user.model.User;

public record DraftInfo(
    CategorySummary category,
    Boolean isLightning,
    String title,
    String content,
    List<String> urls,
    LocalDate startDate,
    LocalDate endDate,
    Integer groupSize,
    List<UserSummary> members,
    PreferGender preferGender,
    Integer preferMinAge,
    Integer preferMaxAge,
    List<String> tags
) {

    public static DraftInfo from(SaveDraftReq request, List<User> members, Category category) {
        return new DraftInfo(
            isNull(category) ? null : CategorySummary.from(category),
            request.isLightning(),
            request.title(),
            request.content(),
            request.urls(),
            request.startDate(),
            request.endDate(),
            request.groupSize(),
            members.stream()
                .map(UserSummary::from)
                .toList(),
            request.preferGender(),
            request.preferMinAge(),
            request.preferMaxAge(),
            request.tags()
        );
    }

}
