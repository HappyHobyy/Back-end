package org.v1.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.handler.MailHandler;
import org.v1.model.Mail;

@Component
@AllArgsConstructor
public class EmailSender {
    private final MailHandler mailHandler;
    public void sendEmail(final Mail mail) {
        mailHandler.sendEmail(mail);
    }
}
