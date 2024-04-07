package org.example.domain.user.service;

import org.example.domain.user.domain.User;

public interface UserService {
    void registerUser(User user);
    User loginUser(User user);
    User loginUserWithKakao(User user);
    User loginUserWithGoogle(User user);
}