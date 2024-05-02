package org.v1.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.v1.domain.user.implementation.UserAppender;
import org.v1.email.ExternalMailService;
import org.v1.email.Mail;
import org.v1.domain.user.domain.User;
import org.v1.domain.user.implementation.UserReader;
import java.security.SecureRandom;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final UserReader userReader;
    private final UserAppender userAppender;
    private final ExternalMailService externalMailService;
    @Override
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
        externalMailService.mailSend(mail);
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

