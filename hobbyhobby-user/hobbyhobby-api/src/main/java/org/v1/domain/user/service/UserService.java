package org.v1.domain.user.service;
import org.v1.domain.user.domain.User;

public interface UserService {
    void registerUser(User user);
    User loginUser(User user);
    User loginUserWithKakao(User user);
    User loginUserWithGoogle(User user);
    void removeUser(Long userId);
    User getMyPage(Long userId);
}