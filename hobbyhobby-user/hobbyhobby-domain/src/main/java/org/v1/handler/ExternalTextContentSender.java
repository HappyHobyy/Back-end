package org.v1.handler;

import org.v1.model.User;

public interface ExternalTextContentSender {
    void sendUserUpdate(User user);
    void sendUserDelete(Long userId);
    void sendUserCreate(User user);
}
