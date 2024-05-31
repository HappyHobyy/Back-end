package org.v1.jpaentity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.v1.model.community.Community;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "liked_community", schema = "hobby_community")
public class LikedCommunityJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liked_community_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private CommunityUserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "community_id", nullable = false)
    private CommunityJpaEntity community;

    public static LikedCommunityJpaEntity of(Long userId, Long communityId)
    {
        return LikedCommunityJpaEntity.builder()
                .user(CommunityUserJpaEntity.onlyWithId(userId))
                .community(CommunityJpaEntity.onlyWithId(communityId))
                .build();
    }

    public Community to(){
        return Community.withId(community.getId().intValue(),community.getName());
    }
}