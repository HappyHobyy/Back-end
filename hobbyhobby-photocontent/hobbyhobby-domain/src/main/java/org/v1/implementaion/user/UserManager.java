package org.v1.implementaion.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.model.user.User;
import org.v1.repository.user.UserRepository;
@Component
@AllArgsConstructor
public class UserManager {
    private final UserRepository repository;
    public void appendUser(final User user){
        repository.appendUser(user);
    }
    public void removeUser(final Long userId) {
        repository.removeUser(userId);
    }
    public void updateUser(final User user) {
        repository.updateUser(user);
    }

}
