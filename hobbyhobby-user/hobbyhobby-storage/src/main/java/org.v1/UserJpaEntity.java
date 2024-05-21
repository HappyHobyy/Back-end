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
@Table(name = "Users")
public class UserJpaEntity  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Nickname")
    private String nickname;

    @Column(name = "Birthday")
    private String birth;

    @Column(name = "HP")
    private String phoneNumber;

    @Column(name = "ImageURL")
    private String imageUrl;

    @Column(name = "Gender")
    private User.UserGender gender;

    @Column(name = "Nationality")
    private User.Nationality nationality;

    @Column(name = "SignUpChannel")
    private User.UserType type;

    @Column(name = "deviceToken")
    private String deviceToken;

//    @Column(name = "role")
//    private User.UserRole role;

    public static UserJpaEntity ofWithoutId(User user) {
        return UserJpaEntity.builder()
                .gender(user.getUserGender())
                .email(user.getEmail())
                .nationality(user.getNationality())
                .nickname(user.getNickname())
                .password(user.getPassword().password())
                .type(user.getUserType())
                .deviceToken(user.getDeviceToken())
                .imageUrl(user.getImageUrl())
                .name(user.getUserName())
                .phoneNumber(user.getPhoneNumber())
                .birth(user.getBirth())
                .build();
    }
    public static UserJpaEntity ofWithId(User user) {
        return UserJpaEntity.builder()
                .id(user.getId().value())
                .gender(user.getUserGender())
                .email(user.getEmail())
                .nationality(user.getNationality())
                .nickname(user.getNickname())
                .password(user.getPassword() != null ? user.getPassword().password() : null)
                .type(user.getUserType())
                .deviceToken(user.getDeviceToken())
                .imageUrl(user.getImageUrl())
                .name(user.getUserName())
                .phoneNumber(user.getPhoneNumber())
                .birth(user.getBirth())
                .build();
    }

    public Optional<User> toUser() {
        User user = User.withId(
                new User.UserId(this.id),
                this.nickname,
                this.email,
                this.type,
                this.password != null ? new User.Password(this.password) : null,
                this.gender,
                this.nationality,
                this.deviceToken,
                this.imageUrl,
                this.name,
                this.phoneNumber,
                this.birth
        );
        return Optional.of(user);
    }
}