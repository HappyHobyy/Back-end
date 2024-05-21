package org.v1.model.community;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Community {
    private final Integer id;
    private final String communityName;
    private final String imageUrl;
    private final Category category;
    public static Community onlyWithId(Integer id) {
        return new Community(id,null,null,null);
    }
    public static Community withId(Integer id,String name, String url) {
        return new Community(id,name,url,null);
    }

}
