package wtwt.domain.post.presentation;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wtwt.common.annotation.Login;
import wtwt.common.doc.swagger.PostSwagger;
import wtwt.common.dto.response.IdResponse;
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

    @PostMapping("/{id}/save")
    public ResponseEntity<IdResponse> save(
        @Login Long loginId,
        @PathVariable Long id,
        @RequestBody @Valid CreatePostApiReq request
    ) {
        Long postId = postService.save(id, request.toCreatePostReq(loginId));
        return ResponseEntity.ok()
            .body(new IdResponse(postId));
    }

    @PostMapping("/save")
    public ResponseEntity<IdResponse> createAndSave(
        @Login Long loginId,
        @RequestBody @Valid CreatePostApiReq request
    ) {
        Long postId = postService.createAndSave(request.toCreatePostReq(loginId));
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/posts/{id}")
            .buildAndExpand(postId)
            .toUri();

        return ResponseEntity.created(uri)
            .body(new IdResponse(postId));
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<IdResponse> publish(
        @Login Long loginId,
        @PathVariable Long id,
        @RequestBody @Valid CreatePostApiReq request
    ) {
        Long postId = postService.publish(id, request.toCreatePostReq(loginId));
        return ResponseEntity.ok()
            .body(new IdResponse(postId));
    }

    @PostMapping("/publish")
    public ResponseEntity<IdResponse> createAndPublish(
        @Login Long loginId,
        @RequestBody @Valid CreatePostApiReq request
    ) {
        Long postId = postService.createAndPublish(request.toCreatePostReq(loginId));
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/posts/{id}")
            .buildAndExpand(postId)
            .toUri();

        return ResponseEntity.created(uri)
            .body(new IdResponse(postId));
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
