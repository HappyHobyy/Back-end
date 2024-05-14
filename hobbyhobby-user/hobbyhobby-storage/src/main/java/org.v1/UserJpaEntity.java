package org.v1;

import jakarta.persistence.*;
import lombok.*;
import org.v1.model.User;
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
    @Column(name = "imageUrl")
    private String imageUrl;
    @Column(name = "name")
    private String name;
    @Column(name = "phoneNumber")
    private Integer phoneNumber;
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
                .imageUrl(user.getImageUrl())
                .name(user.getUserName())
                .phoneNumber(user.getPhoneNumber())
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
                .password(user.getPassword() != null ? user.getPassword().password() : null)
                .type(user.getUserType())
                .deviceToken(user.getDeviceToken())
                .imageUrl(user.getImageUrl())
                .build();
    }

    public Optional<User> toUser() {
        User user = User.withId(
                new User.UserId(this.id),
                this.nickname,
                this.email,
                this.type,
                this.password != null ? new User.Password(this.password) : null,
                this.role,
                this.gender,
                this.nationality,
                this.deviceToken,
                this.imageUrl,
                this.name,
                this.phoneNumber
        );
        return Optional.of(user);
    }
}
