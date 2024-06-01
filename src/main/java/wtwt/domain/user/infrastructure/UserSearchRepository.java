package wtwt.domain.user.infrastructure;

import wtwt.common.dto.response.ScrollResponse;
import wtwt.domain.auth.application.dto.response.UserSummary;
import wtwt.domain.user.presentation.UserSearch;

public interface UserSearchRepository {

    ScrollResponse<UserSummary> search(UserSearch userSearch);
}
