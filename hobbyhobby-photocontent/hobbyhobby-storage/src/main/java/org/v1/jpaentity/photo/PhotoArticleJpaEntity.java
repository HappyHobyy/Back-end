package org.v1.jpaentity.photo;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.v1.jpaentity.community.CommunityJpaEntity;
import org.v1.jpaentity.user.UserJpaEntity;
import org.v1.model.article.PhotoArticleContent;
import org.v1.model.article.PhotoArticle;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@DynamicInsert
@Table(name = "photo_content")
public class PhotoArticleJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_content_id", nullable = false)
    private Long id;

    @Column(name = "content")
    private String content;

    @ColumnDefault("0")
    @Column(name = "comments", nullable = false)
    private Integer comments;

    @ColumnDefault("0")
    @Column(name = "likes", nullable = false)
    private Integer likes;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp", updatable = false, nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", columnDefinition = "timestamp", nullable = false)
    private Instant modifiedAt;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    @BatchSize(size = 100)
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "community_id", nullable = false)
    @BatchSize(size = 100)
    private CommunityJpaEntity community;

    public static PhotoArticleJpaEntity onlyWithId(Long id) {
        return PhotoArticleJpaEntity.builder()
                .id(id)
                .build();
    }
    public PhotoArticle to(){
        return PhotoArticle.withId(
                this.id,
                this.user.toUser().orElseThrow(),
                this.community.getId().intValue(),
                LocalDateTime.ofInstant(createdAt, ZoneId.of("Asia/Seoul")),
                new PhotoArticleContent(this.getContent(),null),
                new PhotoArticle.LikesComments(this.likes,this.comments)
        );
    }
    public static PhotoArticleJpaEntity of(PhotoArticle article){
        return PhotoArticleJpaEntity.builder()
                .community(CommunityJpaEntity.onlyWithId(article.getCommunityId().longValue()))
                .user(UserJpaEntity.onlyWithId(article.getUser().id()))
                .content(article.getContent().getText()).build();
    }
}