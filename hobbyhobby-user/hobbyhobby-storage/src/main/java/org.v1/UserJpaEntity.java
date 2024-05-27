package org.v1;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;
import org.v1.model.User;

import java.time.Instant;
import java.util.Optional;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Entity
@Builder
@Table(name = "users")
public class UserJpaEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "birthday", nullable = false)
    private String birth;

    @Column(name = "phone_number", nullable = false, length = 11)
    private String phoneNumber;

    @ColumnDefault("https://hobbyhobby.s3.ap-northeast-2.amazonaws.com/user/deafult_user_image.png")
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "gender", nullable = false)
    private User.UserGender gender;

    @Column(name = "nationality", nullable = false)
    private User.Nationality nationality;

    @Column(name = "sign_up_channel", nullable = false)
    private User.UserType type;

    @Column(name = "device_token")
    private String deviceToken;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    private Instant modifiedAt;

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