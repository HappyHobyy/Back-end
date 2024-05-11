package org.v1.jpaentity;

import jakarta.persistence.*;
import lombok.*;
import org.v1.model.User;

import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "text_content_user")
public class UserJpaEntity extends BaseEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "imageUrl")
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
