package org.example.domain.user.implemetion;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.user.domain.User;
import org.example.domain.user.repository.UserRepository;
import org.example.error.BusinessException;
import org.example.error.EntityNotFoundException;
import org.example.error.ErrorCode;
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
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }
    public User readByEmail(final User user) {
        return userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_LOGIN_FAIL));
    }

}
