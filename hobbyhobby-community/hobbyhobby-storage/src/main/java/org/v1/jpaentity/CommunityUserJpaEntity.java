package org.v1.jpaentity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.v1.model.user.User;

import java.util.Optional;

@Getter
@Setter
@DynamicInsert
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
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

    public static CommunityUserJpaEntity of(User user) {
        return CommunityUserJpaEntity.builder()
                .id(user.id())
                .nickname(user.nickname())
                .imageUrl(user.imageUrl())
                .build();
    }

    public Optional<User> toUser() {
        User user = User.withId(
                this.id,
                this.nickname,
                this.imageUrl
        );
        return Optional.of(user);
    }
    public static CommunityUserJpaEntity onlyWithId(Long id) {
        return CommunityUserJpaEntity.builder()
                .id(id)
                .build();
    }
}