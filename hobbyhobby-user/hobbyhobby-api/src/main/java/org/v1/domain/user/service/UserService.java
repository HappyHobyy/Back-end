package org.v1.domain.user.service;
import org.v1.domain.user.domain.User;

public interface UserService {
    User getMyPage(Long userId);
    void removeUser(Long userId);
}