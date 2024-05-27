package org.v1.jpaentity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "photo_image_list", schema = "hobby_imageServer")
public class PhotoImageListJpaEntity {
    @Id
    @Column(name = "photo_image_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "photo_conent_id", nullable = false)
    private PhotoArticleJpaEntity photoContent;

    @Column(name = "image_url", nullable = false, length = 256)
    private String imageUrl;

    @ColumnDefault("0")
    @Column(name = "image_order", nullable = false)
    private Short imageOrder;

}