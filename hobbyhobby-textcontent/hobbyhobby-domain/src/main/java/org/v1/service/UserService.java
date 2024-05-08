package org.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.UserAppender;
import org.v1.implementaion.UserReader;
import org.v1.implementaion.UserRemover;
import org.v1.implementaion.UserUpdater;
import org.v1.model.User;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRemover userRemover;
    private final UserUpdater userUpdater;
    private final UserAppender userAppender;
    public void removeUser(final Long userId) {
        userRemover.removeUser(userId);
    }
    public void updateUser(final User user) {
        userUpdater.updateUser(user);
    }
    public void appendUser(final User user) {
        userAppender.appendUser(user);
    }
}
