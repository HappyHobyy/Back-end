package org.v1.implementaion.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.v1.model.User;
import org.v1.repository.UserRepository;

@Component
@AllArgsConstructor
public class UserReader {
    private final UserRepository userRepository;
    public User readUser(final Long userId) {
        return userRepository.readUser(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }
}
