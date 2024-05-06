package org.v1.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PhotoArticle {
    private final Long id;
    private final LocalDateTime date;
    private final User user;
    private final Integer likes;
    private final Integer comments;
    private final String firstImageUrl;
    public static PhotoArticle withoutId(LocalDateTime date, User user, Integer likes, Integer comments){
        return new PhotoArticle(null,date,user,likes,comments,null);
    }
    public PhotoArticle changeUser(User user){
        return new PhotoArticle(this.id,this.date,user,this.likes,this.comments,this.firstImageUrl);
    }
}
