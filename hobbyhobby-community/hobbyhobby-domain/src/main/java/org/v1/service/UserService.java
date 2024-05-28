package org.v1.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.v1.implementation.user.UserManager;
import org.v1.model.user.User;

@Service
@AllArgsConstructor
public class UserService {
    private final UserManager userManager;
    @Transactional
    public void removeUser(final Long userId) {
        userManager.removeUser(userId);
    }
    @Transactional
    public void updateUser(final User user) {
        userManager.updateUser(user);
    }
    public void appendUser(final User user) {
        userManager.appendUser(user);
    }
}
