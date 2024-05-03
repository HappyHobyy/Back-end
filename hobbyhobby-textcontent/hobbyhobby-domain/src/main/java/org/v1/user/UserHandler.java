package org.v1.user;

import org.springframework.stereotype.Component;
import org.v1.Community;

import java.util.List;
public interface UserHandler {
    List<Community> getUserCommunity(Long userId);
}
