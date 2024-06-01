package wtwt.domain.post.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wtwt.common.doc.swagger.PostSwagger;
import wtwt.common.dto.response.ScrollResponse;
import wtwt.domain.post.application.PostService;
import wtwt.domain.post.presentation.dto.PostSearch;
import wtwt.domain.post.presentation.dto.PostSummary;
import wtwt.domain.post.presentation.dto.request.CreatePostApiReq;
import wtwt.domain.post.presentation.dto.response.PostDetailResponse;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController implements PostSwagger {

    private final PostService postService;

    @PostMapping
    @Deprecated
    public ResponseEntity<Void> create(@RequestBody @Valid CreatePostApiReq request) {
        return null;
    }

    @GetMapping
    @Deprecated
    public ResponseEntity<ScrollResponse<PostSummary>> getPosts(PostSearch request) {
        return null;
    }

    @GetMapping("/{id}")
    @Deprecated
    public ResponseEntity<PostDetailResponse> getPost() {
        return null;
    }
}
