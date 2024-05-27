package org.v1.jpaentity.gathering;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.v1.jpaentity.gathering.UnionGatheringJpaEntity;
import org.v1.jpaentity.photo.PhotoArticleJpaEntity;
import org.v1.jpaentity.user.UserJpaEntity;
import org.v1.model.like.Like;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder

@Table(name = "liked_union_gathering", schema = "hobby_imageServer")
public class LikedUnionGatheringJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "liked_union_gathering_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "union_gathering_id", nullable = false)
    private UnionGatheringJpaEntity unionGathering;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    public static LikedUnionGatheringJpaEntity of(Like like) {
        return LikedUnionGatheringJpaEntity.builder()
                .unionGathering(UnionGatheringJpaEntity.onlyWithId(like.articleId()))
                .user(UserJpaEntity.onlyWithId(like.userId()))
                .build();
    }
}