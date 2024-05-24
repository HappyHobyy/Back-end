package org.v1.model.community;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Community {
    private final Integer id;
    private final String communityName;
    private final Category category;
    public static Community onlyWithId(Integer id) {
        return new Community(id,null,null);
    }
    public static Community withId(Integer id,String name) {
        return new Community(id,name,null);
    }

}
