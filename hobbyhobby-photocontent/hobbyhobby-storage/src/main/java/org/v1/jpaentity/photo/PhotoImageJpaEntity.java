package org.v1.jpaentity.photo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.v1.model.imageVideo.ImageVideo;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "photo_image_list", schema = "hobby_imageServer")
public class PhotoImageJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public static PhotoImageJpaEntity of(ImageVideo imageVideo, Long articleId) {
        return PhotoImageJpaEntity.builder()
                .photoContent(PhotoArticleJpaEntity.onlyWithId(articleId))
                .imageUrl(imageVideo.path())
                .imageOrder(imageVideo.index().shortValue())
                .build();
    }
    public ImageVideo toImageVideo(){
        return ImageVideo.withoutData(imageOrder.intValue(),imageUrl, ImageVideo.FileType.H_LOG);
    }
}