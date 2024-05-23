package org.v1.jpaentity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "hot_photo_image_list", schema = "hobby_community")
public class HotPhotoImageListJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hot_photo_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "hot_photo_content_id", nullable = false)
    private HotPhotoContentJpaEntity hotPhotoContent;

    @Column(name = "image_url", nullable = false, length = 256)
    private String imageUrl;

    @Column(name = "image_order", nullable = false)
    private Short imageOrder;

}