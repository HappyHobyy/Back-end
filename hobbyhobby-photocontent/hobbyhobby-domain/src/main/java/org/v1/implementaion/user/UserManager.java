package org.v1.implementaion.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.model.User;
import org.v1.repository.UserRepository;
@Component
@AllArgsConstructor
public class UserManager {
    private final UserRepository userRepository;
    public void appendUser(final User user){
        userRepository.appendUser(user);
    }
    public void removeUser(final Long userId) {
        userRepository.removeUser(userId);
    }
    public void updateUser(final User user) {
        userRepository.updateUser(user);
    }

}