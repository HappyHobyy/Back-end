package org.v1.domain.user.domain;
import lombok.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private final UserId id;
    private final String nickname;
    private final String email;
    private final UserType userType;
    private final Password password;
    private final UserRole userRole;
    private final UserGender userGender;
    private final Nationality nationality;
    private final String deviceToken;
    private final String imageUrl;

    public static User withoutId(String userNickname, String userEmail, UserType userType, Password password,UserRole userRole,UserGender userGender,Nationality nationality,String deviceToken, String imageUrl) {
        return new User(null, userNickname, userEmail, userType, password,userRole,userGender,nationality,deviceToken,imageUrl);
    }
    public static User withId(UserId userId, String userNickname, String userEmail, UserType userType, Password password,UserRole userRole,UserGender userGender,Nationality nationality,String deviceToken,String imageUrl) {
        return new User(userId, userNickname, userEmail, userType, password,userRole,userGender,nationality, deviceToken,imageUrl);
    }
    public record UserId(Long value) {}

    public record Password(String password) {
        public Password hashPassword() {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes());
                return new Password(Base64.getEncoder().encodeToString(hash));
            } catch (NoSuchAlgorithmException e) {
                return null;
            }
        }
        public boolean matches(Password hashedPassword) {
            return this.password.equals(hashedPassword.password);
        }
    }

    public enum UserType {
        OAUTH_DEFAULT,
        OAUTH_GOOGLE,
        OAUTH_KAKAO,
        OAUTH_NAVER
    }
    public enum UserRole {
        ROLE_USER,
        ROLE_ADMIN
    }
    public enum UserGender {
        MAN,
        WOMAN
    }
    public enum Nationality {
        DOMESTIC,
        FOREIGNER
    }
}
