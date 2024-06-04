package org.v1.jpaentity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.v1.model.community.Community;
import org.v1.model.content.GatheringArticle;
import org.v1.model.content.PhotoArticle;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "hot_union_gathering", schema = "hobby_community")
public class HotUnionGatheringJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hot_union_gathering_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private CommunityUserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "community1_id", nullable = false)
    private CommunityJpaEntity community1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "community2_id", nullable = false)
    private CommunityJpaEntity community2;

    @Column(name = "title", nullable = false, length = 256)
    private String title;

    @ColumnDefault("'https://hobbyhobby.s3.ap-northeast-2.amazonaws.com/gathering/default_gathering_image.png'")
    @Column(name = "image_url", nullable = false, length = 256)
    private String imageUrl;

    @Column(name = "joined_count", nullable = false)
    private Integer joinedCount;

    @Column(name = "joined_max", nullable = false)
    private Integer joinedMax;

    @ColumnDefault("0")
    @Column(name = "likes", nullable = false)
    private Integer likes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "is_populist_community", nullable = false)
    private boolean isPopulistCommunity;

    public GatheringArticle to(){
        return GatheringArticle.withId(
                this.id,
                this.title,
                LocalDateTime.ofInstant(createdAt, ZoneId.of("Asia/Seoul")),
                this.user.toUser().orElseThrow(),
                this.joinedMax,
                this.joinedCount,
                this.likes,
                this.imageUrl,
                List.of(
                        Community.withId(this.community1.getId().intValue(),this.community1.getName()),
                        Community.withId(this.community2.getId().intValue(),this.community2.getName())
                        )
        );
    }
}