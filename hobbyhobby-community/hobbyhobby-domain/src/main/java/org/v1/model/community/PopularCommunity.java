package org.v1.model.community;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.v1.model.community.Community;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PopularCommunity {
    private final Integer hotness;
    private final Community community;
}
