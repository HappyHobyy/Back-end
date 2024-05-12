package org.v1.model.user;

import java.time.LocalDateTime;

public record UserStatus(
        Integer userHistoryCount,
        LocalDateTime latestUserAccessTime
) {
    public static UserStatus onlyUserHistoryCount(Integer userHistoryCount) {
        return new UserStatus(userHistoryCount,null);
    }
}
