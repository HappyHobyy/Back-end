package org.v1.model.community;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.user.UserStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCommunity {
    private final UserStatus status;
    private final Community community;
}
