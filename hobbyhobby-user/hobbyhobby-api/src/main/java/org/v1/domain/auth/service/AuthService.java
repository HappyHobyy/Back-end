package org.v1.domain.auth.service;

import org.v1.domain.user.domain.User;

public interface AuthService {
    void registerUser(User user);
    User loginUser(User user);
    User loginUserWithKakao(User user);
    User loginUserWithGoogle(User user);
}
