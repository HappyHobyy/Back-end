package org.v1.jpaentity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.v1.model.community.Community;
import org.v1.model.content.PhotoArticle;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@Entity
@Table(name = "hot_photo_content", schema = "hobby_community")
public class HotPhotoContentJpaEntity {
    @Id
    @Column(name = "hot_photo_content_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private CommunityUserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "community_id", nullable = false)
    private CommunityJpaEntity community;

    @Column(name = "image_url", nullable = false, length = 256)
    private String imageUrl;

    @Column(name = "is_populist_community", nullable = false)
    private boolean isPopulistCommunity;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "likes", nullable = false)
    private Integer likes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public PhotoArticle to(){
        return PhotoArticle.withId(
                this.id,
                LocalDateTime.ofInstant(createdAt, ZoneId.of("Asia/Seoul")),
                this.user.toUser().orElseThrow(),
                this.content,
                this.likes,
                0,
                this.imageUrl,
                Community.withId(this.community.getId().intValue(),this.community.getName())
        );
    }

}