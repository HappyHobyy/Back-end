package org.v1.jpaentity.rental;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.v1.jpaentity.community.CommunityJpaEntity;

@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "rental", schema = "hobby_imageServer")
public class RentalJpaEntity {
    @Id
    @Column(name = "rental_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "community_id", nullable = false)
    private CommunityJpaEntity community;

    @ColumnDefault("'https://hobbyhobby.s3.ap-northeast-2.amazonaws.com/rental/default_rental_image.png'")
    @Column(name = "image_url", nullable = false, length = 256)
    private String imageUrl;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Lob
    @Column(name = "unit", nullable = false)
    private String unit;

    @ColumnDefault("0")
    @Column(name = "likes", nullable = false)
    private Integer likes;

}