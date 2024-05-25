package org.v1.jpaentity.gathering;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.v1.jpaentity.gathering.GatheringJpaEntity;
import org.v1.jpaentity.photo.PhotoArticleJpaEntity;
import org.v1.jpaentity.user.UserJpaEntity;
import org.v1.model.like.Like;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "liked_gathering", schema = "hobby_imageServer")
public class LikedGatheringJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "liked_gathering_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "gathering_id", nullable = false)
    private GatheringJpaEntity gathering;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    public static LikedGatheringJpaEntity of(Like like) {
        return LikedGatheringJpaEntity.builder()
                .gathering(GatheringJpaEntity.onlyWithId(like.articleId()))
                .user(UserJpaEntity.onlyWithId(like.userId())).build();
    }
}