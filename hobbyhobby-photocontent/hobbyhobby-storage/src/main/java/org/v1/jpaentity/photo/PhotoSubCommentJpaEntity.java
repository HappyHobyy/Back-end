package org.v1.jpaentity.photo;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.v1.jpaentity.photo.PhotoCommentJpaEntity;
import org.v1.jpaentity.user.UserJpaEntity;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "photo_sub_comment", schema = "hobby_imageServer")
public class PhotoSubCommentJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_sub_comment_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "photo_comment_id", nullable = false)
    private PhotoCommentJpaEntity photoComment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "modified_at", nullable = false)
    private Instant modifiedAt;

}