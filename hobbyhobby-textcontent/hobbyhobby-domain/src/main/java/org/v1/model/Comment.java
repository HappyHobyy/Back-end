package org.v1.model;

import java.time.LocalDateTime;

public record Comment(Long id, User user, LocalDateTime date, String text, boolean isUserCommentOwner) {
}