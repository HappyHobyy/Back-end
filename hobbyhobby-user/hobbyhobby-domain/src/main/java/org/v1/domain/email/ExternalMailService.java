package org.v1.domain.email;

import org.springframework.stereotype.Service;

@Service
public interface ExternalMailService {
    void sendEmail(Mail mail);
}
