package org.v1.jpaentity.photo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.v1.jpaentity.photo.PhotoArticleJpaEntity;
import org.v1.jpaentity.user.UserJpaEntity;
import org.v1.model.comment.Comment;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "photo_comment", schema = "hobby_imageServer")
public class PhotoCommentJpaEntity {
    @Id
    @Column(name = "photo_comment_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "photo_content_id", nullable = false)
    private PhotoArticleJpaEntity photoContent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "modified_at")
    private Instant modifiedAt;

    public Comment to() {
        return Comment.withId(
                this.id,
                this.user.toUser().orElseThrow(),
                LocalDateTime.ofInstant(createdAt, ZoneId.of("Asia/Seoul")),
                content);
    }

    public static PhotoCommentJpaEntity of(Comment comment,PhotoArticleJpaEntity articleJpa, UserJpaEntity user) {
        return PhotoCommentJpaEntity.builder()
                .id(comment.getId())
                .content(comment.getText())
                .photoContent(articleJpa)
                .user(user)
                .build();
    }
}