package org.v1.jpaentity.gathering;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.v1.model.article.GatheringArticle;
import org.v1.model.article.GatheringArticleContent;
import org.v1.model.article.GatheringInfo;
import org.v1.model.article.PhotoArticleContent;
import org.v1.model.imageVideo.ImageVideo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@DynamicInsert
@Table(name = "gathering_detail", schema = "hobby_imageServer")
public class GatheringDetailJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gathering_detail_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "gathering_id", nullable = false)
    @BatchSize(size = 10)
    private GatheringJpaEntity gathering;
    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "link", nullable = false, length = 256)
    private String link;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "time", nullable = false)
    private LocalDateTime gatheringTime;

    @ColumnDefault("0")
    @Column(name = "likes", nullable = false)
    private Integer likes;

    public GatheringArticleContent toContent() {
        return new GatheringArticleContent(this.content, this.location, this.gatheringTime, this.link);
    }

    public static GatheringDetailJpaEntity of(GatheringArticleContent content, GatheringJpaEntity gathering){
        return GatheringDetailJpaEntity.builder()
                .gathering(gathering)
                .content(content.getDescription())
                .link(content.getOpenTalkLink())
                .location(content.getLocation())
                .gatheringTime(content.getGatheringTime())
                .build();
    }

    public GatheringArticle toGatheringArticle() {
        GatheringInfo info = GatheringInfo.unionGatheringWithCommunity(List.of(this.gathering.getCommunity().getId().intValue()));
        return GatheringArticle.withId(
                this.gathering.getId(),
                this.gathering.getTitle(),
                this.gathering.getUser().toUser().orElseThrow(),
                info,
                this.gathering.getCreatedAt().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime(),
                this.gathering.getCommunityMax().intValue(),
                this.gathering.getJoinedCount(),
                this.likes,
                ImageVideo.withOnlyPath(this.gathering.getImageUrl())
        );
    }
}