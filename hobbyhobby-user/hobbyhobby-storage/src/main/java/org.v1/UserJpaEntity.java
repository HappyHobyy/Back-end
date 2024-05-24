package org.v1;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.v1.model.User;

import java.time.LocalDateTime;
import java.util.Optional;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "birthday")
    private String birth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "gender")
    private User.UserGender gender;

    @Column(name = "nationality")
    private User.Nationality nationality;

    @Column(name = "sign_up_channel")
    private User.UserType type;

    @Column(name = "device_token")
    private String deviceToken;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", columnDefinition = "timestamp")
    private LocalDateTime modifiedAt;

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