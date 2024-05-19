package org.v1.model.article;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.user.User;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PhotoArticle {
    private final Long id;
    private final Integer communityId;
    private final LocalDateTime date;
    private final User user;
    private final LikesComments likesComments;
    private final String mainImageUrl;
    public static PhotoArticle initial(User user,Integer communityId){
        return new PhotoArticle(null,communityId,LocalDateTime.now(),user,null,null);
    }
    public record LikesComments(Integer likes, Integer comments) {
    }
    public PhotoArticle withLikesComments(LikesComments likesComments) {
        return new PhotoArticle(this.id,this.communityId, this.date, this.user, likesComments,this.mainImageUrl);
    }
}
