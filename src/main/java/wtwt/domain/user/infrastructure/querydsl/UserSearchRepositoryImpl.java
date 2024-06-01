package wtwt.domain.user.infrastructure.querydsl;

import static wtwt.domain.user.model.QUser.user;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wtwt.common.dto.response.ScrollResponse;
import wtwt.domain.auth.application.dto.response.UserSummary;
import wtwt.domain.user.infrastructure.UserSearchRepository;
import wtwt.domain.user.presentation.UserSearch;

@Repository
@RequiredArgsConstructor
public class UserSearchRepositoryImpl implements UserSearchRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public ScrollResponse<UserSummary> search(UserSearch searchOption) {
        List<UserSummary> userSummaries = jpaQueryFactory.select(projectUserSummary())
            .from(user)
            .where(greaterThanCursor(searchOption.cursor()),
                containsKeyword(searchOption.keyword()))
            .limit(searchOption.size())
            .fetch();

        return ScrollResponse.<UserSummary>builder()
            .contents(userSummaries)
            .isEmpty(userSummaries.isEmpty())
            .nextCursor(getNextCursor(userSummaries))
            .scrollSize(searchOption.size())
            .build();
    }

    private BooleanExpression containsKeyword(String keyword) {
        return user.nickname.contains(keyword);
    }

    private BooleanExpression greaterThanCursor(String cursor) {
        Long userCursor = cursor.isEmpty() ? 0 : Long.parseLong(cursor);
        return user.id.gt(userCursor);
    }

    private ConstructorExpression<UserSummary> projectUserSummary() {
        return Projections.constructor(
            UserSummary.class, user.id, user.nickname, user.profileImageUrl);
    }

    private String getNextCursor(List<UserSummary> userSummaries) {
        if (userSummaries.isEmpty()) {
            return null;
        }
        return String.valueOf(userSummaries.get(userSummaries.size() - 1).id());
    }
}
