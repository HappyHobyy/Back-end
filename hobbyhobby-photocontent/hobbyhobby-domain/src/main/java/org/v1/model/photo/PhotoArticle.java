package org.v1.model.photo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.user.User;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PhotoArticle {
    private final Long id;
    private final LocalDateTime date;
    private final User user;
    private final LikesComments likesComments;
    private final String mainImageUrl;
    public static PhotoArticle withoutId(LocalDateTime date, User user){
        return new PhotoArticle(null,date,user,null,null);
    }
    public record LikesComments(Integer likes, Integer comments) {
    }
    public PhotoArticle withLikesComments(LikesComments likesComments) {
        return new PhotoArticle(this.id, this.date, this.user, likesComments,this.mainImageUrl);
    }
}
