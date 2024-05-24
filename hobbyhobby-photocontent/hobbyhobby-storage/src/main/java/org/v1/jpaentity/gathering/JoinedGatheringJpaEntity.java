package org.v1.jpaentity.gathering;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.v1.jpaentity.gathering.GatheringJpaEntity;
import org.v1.jpaentity.user.UserJpaEntity;

@Getter
@Setter
@Entity
@Table(name = "joined_gathering", schema = "hobby_imageServer")
public class JoinedGatheringJpaEntity {
    @Id
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

}