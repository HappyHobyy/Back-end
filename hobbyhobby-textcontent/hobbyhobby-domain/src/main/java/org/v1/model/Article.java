package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Article {
    private final Long id;
    private final String title;
    private final LocalDateTime date;
    private final User user;
    private final Integer likes;
    private final Integer comments;
    public static Article withoutId(String title, LocalDateTime date, User user, Integer likes,Integer comments){
        return new Article(null,title,date,user,likes,comments);
    }
    public Article changeUser(User user){
        return new Article(this.id,this.title,this.date,user,this.likes,this.comments);
    }
}
