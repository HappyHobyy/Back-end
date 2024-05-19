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
        User updatedUser = user.resetUserPassword();
        userAppender.appendUser(updatedUser);
        Mail mail = Mail.from(user, updatedUser.getPassword().password());
        emailSender.sendEmail(mail);
    }
}

