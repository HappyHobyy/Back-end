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
@Table(name = "union_gathering", schema = "hobby_imageServer")
public class UnionGatheringJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "union_gathering_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    @BatchSize(size = 10)
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "community1_id", nullable = false)
    @BatchSize(size = 10)
    private CommunityJpaEntity community1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "community2_id", nullable = false)
    @BatchSize(size = 10)
    private CommunityJpaEntity community2;

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
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "modified_at")
    private Instant modifiedAt;

    public static UnionGatheringJpaEntity onlyWithId(Long id) {
        return UnionGatheringJpaEntity.builder()
                .id(id)
                .build();
    }

    public static UnionGatheringJpaEntity of(GatheringArticle article) {
        return UnionGatheringJpaEntity.builder()
                .community1(CommunityJpaEntity.onlyWithId(article.getInfo().communityIds().get(0).longValue()))
                .community2(CommunityJpaEntity.onlyWithId(article.getInfo().communityIds().get(1).longValue()))
                .user(UserJpaEntity.onlyWithId(article.getUser().id()))
                .communityMax(article.getJoinedMax().shortValue())
                .title(article.getTitle()).build();
    }

    public ImageVideo toImageVideo() {
        return ImageVideo.withOnlyPath(imageUrl);
    }
}