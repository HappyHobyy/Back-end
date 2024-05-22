package org.v1.model.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public record UserStatus(boolean isUserLiked, boolean isUserOwner, boolean isUserJoined) {
}