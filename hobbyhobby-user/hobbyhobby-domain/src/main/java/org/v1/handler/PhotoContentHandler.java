package org.v1.handler;

import org.v1.model.User;

public interface PhotoContentHandler {
    void sendUserUpdate(User user);
    void sendUserDelete(Long userId);
    void sendUserCreate(User user);
}
