package org.v1.domain.user.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.v1.domain.user.domain.User;
import org.v1.domain.user.repository.UserRepository;
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
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND)).toUser();
    }
    public User readUserByTypeAndEmail(final User user){
        return userRepository.findUserJpaEntitiesByEmailAndType(user.getEmail(), user.getUserType())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_EMAIL_NOT_FOUND)).toUser();
    }
    public User readUserByEmail(final String userEmail){
        return userRepository.findUserJpaEntitiesByEmail(userEmail)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_EMAIL_NOT_FOUND)).toUser();
    }
}
