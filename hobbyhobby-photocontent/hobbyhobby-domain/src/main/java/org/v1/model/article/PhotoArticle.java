package org.v1.model.article;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.comment.Comment;
import org.v1.model.user.User;
import org.v1.model.user.UserStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PhotoArticle {
    private final Long id;
    private final Integer communityId;
    private final LocalDateTime date;
    private final User user;
    private final LikesComments likesComments;
    private final UserStatus userStatus;
    private final PhotoAriticleContent content;
    public static PhotoArticle initial(User user,Integer communityId, PhotoAriticleContent content){
        return new PhotoArticle(null,communityId,LocalDateTime.now(),user,null,null,content);
    }
    public record LikesComments(Integer likes, Integer comments) {
    }
    public PhotoArticle updateUserStatus(UserStatus status) {
        return new PhotoArticle(id,communityId,date,user,likesComments,status,content);
    }
    public boolean isUserArticleOwner(Long userId){
        return user.id().equals(userId);
    }
}
