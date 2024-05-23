package org.v1.jpaentity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "image_server_community")
public class CommunityJpaEntity {
    @Id
    @Column(name = "community_id")
    private Long id;

    @Column(name = "community_name")
    private String communityName;
    }
