package org.v1.jpaentity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "gathering", schema = "hobby_imageServer")
public class GatheringJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gathering_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "community_id", nullable = false)
    private CommunityJpaEntity community;

    @Column(name = "community_max", nullable = false)
    private Short communityMax;

    @Column(name = "title", nullable = false, length = 256)
    private String title;

    @ColumnDefault("'https://hobbyhobby.s3.ap-northeast-2.amazonaws.com/gathering/default_gathering_image.png'")
    @Column(name = "image_url", nullable = false, length = 256)
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", columnDefinition = "timestamp")
    private LocalDateTime modifiedAt;

}