package org.v1.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.handler.ExternalMailSender;
import org.v1.model.Mail;

@Component
@AllArgsConstructor
public class EmailSender {
    private final ExternalMailSender externalMailSender;
    public void sendEmail(final Mail mail) {
        externalMailSender.sendEmail(mail);
    }
}
