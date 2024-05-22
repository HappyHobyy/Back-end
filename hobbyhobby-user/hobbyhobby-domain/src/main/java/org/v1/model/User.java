package org.v1.model;
import lombok.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private final UserId id;
    private final String nickname;
    private final String email;
    private final UserType userType;
    private final Password password;
    private final UserGender userGender;
    private final Nationality nationality;
    private final String deviceToken;
    private final String imageUrl;
    private final String userName;
    private final String phoneNumber;
    private final String birth;

    public static User withoutId(String userNickname, String userEmail, UserType userType, Password password,UserGender userGender,Nationality nationality,String deviceToken, String imageUrl,String userName,String phoneNumber,String birth) {
        return new User(null, userNickname, userEmail, userType, password,userGender,nationality,deviceToken,imageUrl,userName,phoneNumber,birth);
    }
    public static User withId(UserId userId, String userNickname, String userEmail, UserType userType, Password password,UserGender userGender,Nationality nationality,String deviceToken,String imageUrl,String userName,String phoneNumber,String birth) {
        return new User(userId, userNickname, userEmail, userType, password,userGender,nationality, deviceToken,imageUrl,userName, phoneNumber,birth);
    }
    public User registDefaultUser(){
        Password hashedPassword = this.password.hashPassword();
        return new User(null, nickname, email, userType, hashedPassword, userGender, nationality, deviceToken, imageUrl,userName,phoneNumber,birth);
    }
    public User updateUserDeviceToken(String newDeviceToken) {
        return new User(id, nickname, email, userType, password, userGender, nationality, newDeviceToken, imageUrl,userName,phoneNumber,birth);
    }
    public User updateUserImage(String imageUrl) {
        return new User(id, nickname, email, userType, password, userGender, nationality, deviceToken, imageUrl,userName,phoneNumber,birth);
    }
    public User resetUserPassword(){
        Password resetPassword = this.password.tempPassword();
        return new User(id, nickname, email, userType, resetPassword, userGender, nationality, deviceToken, imageUrl,userName,phoneNumber,birth);
    }
    public record UserId(Long value) {}

    public record Password(String password) {
        public Password hashPassword()  {
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
        public Password tempPassword() {
            SecureRandom random = new SecureRandom();
            String charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^?";
            return new Password(random.ints(10, 0, charSet.length())
                    .mapToObj(charSet::charAt)
                    .map(Object::toString)
                    .collect(Collectors.joining()));
        }
    }

    public enum UserType {
        OAUTH_DEFAULT,
        OAUTH_GOOGLE,
        OAUTH_KAKAO,
        OAUTH_NAVER
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
