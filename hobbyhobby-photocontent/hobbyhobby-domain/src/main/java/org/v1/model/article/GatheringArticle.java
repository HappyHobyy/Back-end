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
    private final String title;
    private final GatheringInfo info;
    private final LocalDateTime date;
    private final User user;
    private final Integer countUsers;
    private final Integer likes;
    private final ImageVideo imageVideo;
    public static GatheringArticle initial(User user,String title, GatheringInfo type){
        return new GatheringArticle(null,title,type,LocalDateTime.now(),user,0,0,null);
    }
    public static GatheringArticle withId(Long id,String title,User user,GatheringInfo info,LocalDateTime date,Integer countUsers,Integer likes, ImageVideo image){
        return new GatheringArticle(id,title,info,date,user,countUsers,likes,image);
    }
}
