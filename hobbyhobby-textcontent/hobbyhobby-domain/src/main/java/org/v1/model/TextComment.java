package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TextComment {
    private Long id;
    private Comment comment;
    public static TextComment withoutId(Comment comment) {
        return new TextComment(null,comment);
    }
    public TextComment changeComment(Comment comment) {
        return new TextComment(id,comment);
    }
}
