package org.v1.jpaentity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "community_user", schema = "hobby_community")
public class CommunityUserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_user_id", nullable = false)
    private Long id;

    @Column(name = "nickname", nullable = false, length = 30)
    private String nickname;

    @ColumnDefault("'https://hobbyhobby.s3.ap-northeast-2.amazonaws.com/user/deafult_user_image.png'")
    @Column(name = "image_url", nullable = false, length = 256)
    private String imageUrl;

}