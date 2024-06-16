package wtwt.domain.draft.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wtwt.common.base.BaseTimeEntity;
import wtwt.domain.draft.model.converter.DraftInfoConverter;
import wtwt.domain.user.model.User;
import wtwt.exception.custom.ForbiddenException;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Draft extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User writer;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Convert(converter = DraftInfoConverter.class)
    @Column(name = "draft", nullable = false, length = 4000)
    private DraftInfo draft;

    @Builder
    public Draft(Long id, User writer, String title, DraftInfo draft) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.draft = draft;
    }

    public void assertWriter(Long userId) {
        if (!writer.getId().equals(userId)) {
            throw new ForbiddenException("해당 임시저장 게시물의 작성자가 아닙니다.");
        }
    }
}
