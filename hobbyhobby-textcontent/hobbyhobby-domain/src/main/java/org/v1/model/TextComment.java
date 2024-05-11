package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TextComment {
    private Long id;
    private String text;
    private Comment comment;
    public static TextComment withoutId(Comment comment,String text) {
        return new TextComment(null,text,comment);
    }
    public TextComment changeComment(Comment comment) {
        return new TextComment(id,text,comment);
    }
}
