package org.v1.jpaentity.gathering;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.v1.jpaentity.community.CommunityJpaEntity;
import org.v1.jpaentity.gathering.GatheringJpaEntity;
import org.v1.jpaentity.user.UserJpaEntity;
import org.v1.model.group.GatheringMember;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "joined_gathering", schema = "hobby_imageServer")
public class JoinedGatheringJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "joined_gathering_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "gathering_id", nullable = false)
    private GatheringJpaEntity gathering;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "community_id", nullable = false)
    private CommunityJpaEntity community;

    public static JoinedGatheringJpaEntity of(GatheringMember gatheringMember, CommunityJpaEntity entity) {
        return JoinedGatheringJpaEntity.builder()
                .gathering(GatheringJpaEntity.onlyWithId(gatheringMember.articleId()))
                .community(entity)
                .user(UserJpaEntity.onlyWithId(gatheringMember.userId())).build();
    }
}