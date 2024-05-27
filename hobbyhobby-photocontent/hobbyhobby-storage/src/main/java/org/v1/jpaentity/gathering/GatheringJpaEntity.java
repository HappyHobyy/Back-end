package org.v1.jpaentity.gathering;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.v1.jpaentity.community.CommunityJpaEntity;
import org.v1.jpaentity.user.UserJpaEntity;
import org.v1.model.article.GatheringArticle;
import org.v1.model.imageVideo.ImageVideo;

import java.time.Instant;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@DynamicInsert
@Table(name = "gathering", schema = "hobby_imageServer")
public class GatheringJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gathering_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    @BatchSize(size = 10)
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "community_id", nullable = false)
    @BatchSize(size = 10)
    private CommunityJpaEntity community;

    @Column(name = "community_max", nullable = false)
    private Short communityMax;

    @ColumnDefault("0")
    @Column(name = "joined_count", nullable = false)
    private Integer joinedCount;

    @Column(name = "title", nullable = false, length = 256)
    private String title;

    @ColumnDefault("'https://hobbyhobby.s3.ap-northeast-2.amazonaws.com/gathering/default_gathering_image.png'")
    @Column(name = "image_url", nullable = false, length = 256)
    private String imageUrl;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "modified_at", nullable = false)
    private Instant modifiedAt;

    public static GatheringJpaEntity onlyWithId(Long id) {
        return GatheringJpaEntity.builder()
                .id(id)
                .build();
    }
    public  ImageVideo toImageVideo(){
        return ImageVideo.withOnlyPath(imageUrl);
    }

    public static GatheringJpaEntity of(GatheringArticle article) {
        return GatheringJpaEntity.builder()
                .community(CommunityJpaEntity.onlyWithId(article.getInfo().communityIds().get(0).longValue()))
                .user(UserJpaEntity.onlyWithId(article.getUser().id()))
                .communityMax(article.getJoinedMax().shortValue())
                .title(article.getTitle()).build();
    }
}