package org.v1.jpaentity.photo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.v1.jpaentity.photo.PhotoArticleJpaEntity;
import org.v1.jpaentity.user.UserJpaEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "liked_photo", schema = "hobby_imageServer")
public class LikedPhotoJpaEntity {
    @Id
    @Column(name = "liked_photo_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "photo_content_id", nullable = false)
    private PhotoArticleJpaEntity photoContent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    public static LikedPhotoJpaEntity onlyWithId(Long id) {
        return LikedPhotoJpaEntity.builder()
                .id(id)
               .build();
    }
    public static LikedPhotoJpaEntity of(PhotoArticleJpaEntity photoContent, UserJpaEntity user) {
        return LikedPhotoJpaEntity.builder()
                .photoContent(photoContent)
               .user(user).build();
    }

}