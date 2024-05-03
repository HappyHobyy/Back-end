package org.v1.domain.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.v1.domain.user.implementation.UserAppender;
import org.v1.domain.user.domain.User;
import org.v1.domain.user.implementation.UserReader;
import java.security.SecureRandom;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class EmailService {
    private final UserReader userReader;
    private final UserAppender userAppender;
    private final ExternalMailService externalMailService;
    @Transactional
    public void sendEmailAndChangePassword(final String userEmail) {
        User user = userReader.readUserByEmail(userEmail);
        User.Password randomPassword = new User.Password(getTempPassword());
        User hashedUser = User.withoutId(
                user.getNickname(),
                user.getEmail(),
                user.getUserType(),
                randomPassword.hashPassword(),
                user.getUserRole(),
                user.getUserGender(),
                user.getNationality(),
                user.getDeviceToken()
        );
        userAppender.appendUser(hashedUser);
        Mail mail = Mail.from(user,randomPassword.password());
        externalMailService.sendEmail(mail);
    }

    public String getTempPassword() {
        SecureRandom random = new SecureRandom();
        String charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^?";
        return random.ints(10, 0, charSet.length())
                .mapToObj(charSet::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}

