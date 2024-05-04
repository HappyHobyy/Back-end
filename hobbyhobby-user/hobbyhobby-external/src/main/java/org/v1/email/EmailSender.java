package org.v1.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.v1.domain.email.ExternalMailService;
import org.v1.domain.email.Mail;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;

@Component
@RequiredArgsConstructor
public class EmailSender implements ExternalMailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String hostMail;

    @Override
    public void sendEmail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(hostMail);
        message.setTo(mail.message());
        message.setSubject(mail.title());
        message.setText(mail.message());
        try {
            mailSender.send(message);
        } catch (MailException e) {
            throw new BusinessException(ErrorCode.USER_EMAIL_SEND_FAIL);
        }
    }
}
