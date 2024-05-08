package org.v1.handler;

import org.springframework.stereotype.Service;
import org.v1.model.Mail;

@Service
public interface MailHandler {
    void sendEmail(Mail mail);
}
