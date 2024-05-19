package org.v1.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.v1.model.User;
import org.v1.repository.UserRepository;
import org.v1.error.BusinessException;
import org.v1.error.ErrorCode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserReader {
    private final UserRepository userRepository;
    public User readById(final Long userId) {
        return userRepository.readById(userId).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }
    public User readUserByEmail(final String userEmail){
        return userRepository.readByEmail(userEmail).orElseThrow(() -> new BusinessException(ErrorCode.USER_EMAIL_NOT_FOUND));
    }
}
