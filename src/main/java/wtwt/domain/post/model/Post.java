package wtwt.domain.post.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wtwt.common.base.BaseTimeEntity;
import wtwt.domain.post.model.enums.PostStatus;
import wtwt.domain.trip.model.Trip;
import wtwt.domain.user.model.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User writer;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "content", nullable = false, length = 3000)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20, columnDefinition = "VARCHAR")
    private PostStatus status = PostStatus.DRAFT;

    @Column(name = "is_lightning", nullable = false, columnDefinition = "TINYINT")
    private boolean isLightning = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @OneToMany(mappedBy = "post")
    private List<PostFile> images = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<PostTag> tags = new ArrayList<>();

    @Column(name = "hits", nullable = false)
    private int hits = 0;

    //== 생성 메서드 ==//
    private Post(String title, String content, User writer, PostStatus status, Boolean isLightning,
        Trip trip) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.status = status;
        this.isLightning = isLightning;
        this.trip = trip;
    }

    //== 수정 메서드 ==//
    public void modifyPost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addImage(String url) {
        this.images.add(
            PostFile.builder()
                .post(this)
                .url(url)
                .build()
        );
    }

    public void increaseHits() {
        hits += 1;
    }
}
