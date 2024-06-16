package wtwt.domain.draft.application;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtwt.domain.category.infrastructure.CategoryRepository;
import wtwt.domain.draft.application.dto.request.SaveDraftReq;
import wtwt.domain.draft.application.dto.response.DraftSummary;
import wtwt.domain.draft.infrastructure.DraftRepository;
import wtwt.domain.draft.model.Draft;
import wtwt.domain.draft.model.DraftInfo;
import wtwt.domain.user.infrastructure.UserRepository;
import wtwt.domain.user.model.User;
import wtwt.exception.custom.ForbiddenException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DraftService {

    private final DraftRepository draftRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long save(SaveDraftReq request) {
        Draft draft = Draft.builder()
            .id(request.draftId().orElse(null))
            .title(request.title())
            .writer(getUserOrElseThrow(request.loginUserId(), "해당 ID의 유저가 존재하지 않습니다."))
            .draft(DraftInfo.from(
                request,
                toMembers(request.members()),
                categoryRepository.findById(request.categoryId()).orElse(null))
            )
            .build();

        return draftRepository.save(draft)
            .getId();
    }

    public List<DraftSummary> getAllByUser(Long loginId, Long userId) {
        User user = getUserOrElseThrow(userId, "해당 ID의 유저가 존재하지 않습니다.");

        if (!loginId.equals(userId)) {
            throw new ForbiddenException("본인의 임시저장 게시물만 조회할 수 있습니다.");
        }

        return draftRepository.findByWriter(user)
            .stream()
            .map(DraftSummary::from)
            .toList();
    }

    public DraftInfo get(Long loginId, Long id) {
        Draft draft = draftRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시글이 존재하지 않습니다."));

        draft.assertWriter(loginId);

        return draft.getDraft();
    }

    private List<User> toMembers(List<Long> members) {
        return members.stream()
            .map(userId -> getUserOrElseThrow(userId, "해당 ID의 멤버가 존재하지 않습니다."))
            .toList();
    }

    private User getUserOrElseThrow(Long userId, String message) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException(message));
    }
}
