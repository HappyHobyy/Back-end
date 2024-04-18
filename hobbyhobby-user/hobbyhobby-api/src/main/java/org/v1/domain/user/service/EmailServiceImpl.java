package org.v1.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.v1.email.ExternalMailService;
import org.v1.email.Mail;
import org.v1.domain.user.domain.User;
import org.v1.domain.user.implementation.UserReader;
import org.v1.global.config.security.password.PasswordService;
import java.security.SecureRandom;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final UserReader userReader;
    private final PasswordService passwordService;
    private final ExternalMailService externalMailService;
    @Override
    @Transactional
    public void sendEmailAndChangePassword(final String userEmail) {
        User user = userReader.readUserByEmail(userEmail);
        String randomPassword = getTempPassword();
        String hashedPassword = passwordService.encodePassword(randomPassword);
        user.hashedPassword(hashedPassword);
        Mail mail = Mail.from(user,randomPassword);
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

