package org.v1.model;

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
}
