package org.v1.jpaentity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "union_gathering", schema = "hobby_imageServer")
public class UnionGatheringJpaEntity {
    @Id
    @Column(name = "union_gathering_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "community1_id", nullable = false)
    private CommunityJpaEntity community1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "community2_id", nullable = false)
    private CommunityJpaEntity community2;

    @Column(name = "community1_max", nullable = false)
    private Short community1Max;

    @Column(name = "community2_max", nullable = false)
    private Short community2Max;

    @Column(name = "title", nullable = false, length = 256)
    private String title;

    @ColumnDefault("'https://hobbyhobby.s3.ap-northeast-2.amazonaws.com/gathering/default_gathering_image.png'")
    @Column(name = "image_url", nullable = false, length = 256)
    private String imageUrl;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "`modified-at`")
    private Instant modifiedAt;

}