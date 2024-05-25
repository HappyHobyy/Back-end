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
    private final LocalDateTime createdAt;
    private final User user;
    private final Integer joinedMax;
    private final Integer joinedCount;
    private final Integer likes;
    private final ImageVideo imageVideo;
    public static GatheringArticle initial(User user,String title, GatheringInfo info, ImageVideo imageVideo, Integer joinMax){
        return new GatheringArticle(null,title,info,LocalDateTime.now(),user,joinMax,0,0,imageVideo);
    }
    public static GatheringArticle withId(Long id,String title,User user,GatheringInfo info,LocalDateTime date,Integer joinedMax,Integer joinedCount,Integer likes, ImageVideo image){
        return new GatheringArticle(id,title,info,date,user,joinedMax,joinedCount,likes,image);
    }
}
