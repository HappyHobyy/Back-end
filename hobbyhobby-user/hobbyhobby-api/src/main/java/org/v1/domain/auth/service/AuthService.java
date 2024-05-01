package org.v1.domain.auth.service;

import org.v1.domain.user.domain.User;

public interface AuthService {
    void registerDefaultUser(User user);
    void registerOAuthUser(User user);
    User loginDefaultUser(User user);
    User loginOAuthUser(User user);
    void checkEmailDuplicate(User user);
    void checkNicknameDuplicate(User user);
}
