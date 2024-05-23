package org.v1.jpaentity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "community", schema = "hobby_community")
public class CommunityJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 256)
    private String name;

    @Column(name = "image_url", nullable = false, length = 256)
    private String imageUrl;

    @ColumnDefault("0")
    @Column(name = "total_likes", nullable = false)
    private Integer totalLikes;

}