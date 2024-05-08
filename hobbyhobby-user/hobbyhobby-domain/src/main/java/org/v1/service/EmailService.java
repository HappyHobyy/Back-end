package org.v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.v1.implementation.EmailSender;
import org.v1.model.Mail;
import org.v1.implementation.UserAppender;
import org.v1.model.User;
import org.v1.implementation.UserReader;
import java.security.SecureRandom;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class EmailService {
    private final UserReader userReader;
    private final UserAppender userAppender;
    private final EmailSender emailSender;
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
                user.getDeviceToken(),
                user.getImageUrl()
        );
        userAppender.appendUser(hashedUser);
        Mail mail = Mail.from(user,randomPassword.password());
        emailSender.sendEmail(mail);
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

