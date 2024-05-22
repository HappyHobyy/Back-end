package org.v1.model.article;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.imageVideo.ImageVideo;
import org.v1.model.user.User;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GatheringArticle {
    private final Long id;
    private final GatheringInfo info;
    private final LocalDateTime date;
    private final User user;
    private final Integer countUsers;
    private final Integer likes;
    private final ImageVideo imageVideo;
    public static GatheringArticle initial(User user, GatheringInfo type){
        return new GatheringArticle(null,type,LocalDateTime.now(),user,0,0,null);
    }
    public static GatheringArticle withId(Long id,User user,GatheringInfo info,LocalDateTime date,Integer countUsers,Integer likes, ImageVideo image){
        return new GatheringArticle(id,info,date,user,countUsers,likes,image);
    }
}
