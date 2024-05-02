package org.v1;

import jakarta.persistence.*;
import lombok.*;
import org.v1.domain.BaseEntity;
import org.v1.domain.user.domain.User;
import java.util.Optional;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "user")
public class UserJpaEntity  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    private User.UserType type;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private User.UserRole role;

    @Column(name = "gender")
    private User.UserGender gender;

    @Column(name = "nationality")
    private User.Nationality nationality;
    @Column(name = "deviceToken")
    private String deviceToken;
    public static UserJpaEntity ofWithoutId(User user) {
        return UserJpaEntity.builder()
                .gender(user.getUserGender())
                .role(user.getUserRole())
                .email(user.getEmail())
                .nationality(user.getNationality())
                .nickname(user.getNickname())
                .password(user.getPassword().password())
                .type(user.getUserType())
                .deviceToken(user.getDeviceToken())
                .build();
    }
    public static UserJpaEntity ofWithId(User user) {
        return UserJpaEntity.builder()
                .id(user.getId().value())
                .gender(user.getUserGender())
                .role(user.getUserRole())
                .email(user.getEmail())
                .nationality(user.getNationality())
                .nickname(user.getNickname())
                .password(user.getPassword().password())
                .type(user.getUserType())
                .deviceToken(user.getDeviceToken())
                .build();
    }

    public Optional<User> toUser() {
        User user = User.withId(
                new User.UserId(this.id),
                this.nickname,
                this.email,
                this.type,
                new User.Password(this.password),
                this.role,
                this.gender,
                this.nationality,
                this.deviceToken
        );
        return Optional.of(user);
    }
}
