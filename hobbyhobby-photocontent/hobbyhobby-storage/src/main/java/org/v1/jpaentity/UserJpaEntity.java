package org.v1.jpaentity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.v1.model.user.User;

import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "image_server_user")
public class UserJpaEntity {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @ColumnDefault("https://hobbyhobby.s3.ap-northeast-2.amazonaws.com/user/deafult_user_image.png")
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    public static UserJpaEntity of(User user) {
        return UserJpaEntity.builder()
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
}
