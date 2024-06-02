package wtwt.domain.post.model.enums;

import java.util.Arrays;

public enum PostStatus {
    PUBLISHED, DRAFT;

    public static PostStatus from(String status) {
        if (status == null || status.isBlank()) {
            return PUBLISHED;
        }
        return Arrays.stream(PostStatus.values())
            .filter(postStatus -> postStatus.name().equals(status.toUpperCase()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 상태입니다."));
    }
}
