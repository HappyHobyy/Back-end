package org.v1.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementaion.user.UserAppender;
import org.v1.implementaion.user.UserRemover;
import org.v1.implementaion.user.UserUpdater;
import org.v1.model.User;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRemover userRemover;
    private final UserUpdater userUpdater;
    private final UserAppender userAppender;
    @Transactional
    public void removeUser(final Long userId) {
        userRemover.removeUser(userId);
    }
    @Transactional
    public void updateUser(final User user) {
        userUpdater.updateUser(user);
    }
    public void appendUser(final User user) {
        userAppender.appendUser(user);
    }
}
