package org.v1.user;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.v1.Community;

import java.util.List;


@Component
@AllArgsConstructor
public class UserService {
    private final UserHandler userHandler;
    public List<Community> getUserCommunity(Long userId){
        return userHandler.getUserCommunity(userId);
    };
}
