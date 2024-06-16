package wtwt.domain.draft.presentation;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wtwt.common.annotation.Login;
import wtwt.common.doc.swagger.DraftSwagger;
import wtwt.common.dto.response.IdResponse;
import wtwt.domain.draft.application.DraftService;
import wtwt.domain.draft.application.dto.response.DraftSummary;
import wtwt.domain.draft.model.DraftInfo;
import wtwt.domain.post.presentation.dto.request.SaveDraftApiReq;

@RestController
@RequestMapping("/api/v1/drafts")
@RequiredArgsConstructor
public class DraftController implements DraftSwagger {

    private final DraftService draftService;

    @PostMapping("/save")
    public ResponseEntity<IdResponse> save(
        @Login Long loginId,
        @RequestBody @Valid SaveDraftApiReq request
    ) {
        Long postId = draftService.save(request.toSaveDraftReq(loginId));
        return ResponseEntity.ok()
            .body(new IdResponse(postId));
    }

    @GetMapping
    public ResponseEntity<List<DraftSummary>> getAllByUser(
        @Login Long loginId,
        @RequestParam Long userId
    ) {
        List<DraftSummary> response = draftService.getAllByUser(loginId, userId);
        return ResponseEntity.ok()
            .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DraftInfo> get(
        @Login Long loginId,
        @PathVariable Long id
    ) {
        DraftInfo response = draftService.get(loginId, id);
        return ResponseEntity.ok()
            .body(response);
    }

}
