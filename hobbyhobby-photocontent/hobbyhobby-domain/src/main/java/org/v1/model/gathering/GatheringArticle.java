package org.v1.model.gathering;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.user.User;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GatheringArticle {
    private final Long id;
    private final LocalDateTime date;
    private final User user;
    private final Integer usersCount;
    private final String imageUrl;
    public static GatheringArticle withoutId(LocalDateTime date, User user){
        return new GatheringArticle(null,date,user,null,null);
    }
    public GatheringArticle withGatherUsers(Integer usersCount) {
        return new GatheringArticle(this.id, this.date, this.user, usersCount,this.imageUrl);
    }
}
