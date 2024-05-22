package org.v1;

import org.springframework.stereotype.Repository;
import org.v1.model.community.Community;
import org.v1.model.community.UserCommunity;
import org.v1.repository.CommunityRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommunityEntityRepository implements CommunityRepository {

    @Override
    public List<Community> readUserCommunities(Long userId) {
        ArrayList<Community> result = new ArrayList<Community>();
        result.add(Community.withId(1, "낭독 봉사"));
        result.add(Community.withId(2, "가죽 공예"));
        result.add(Community.withId(3, "골프"));
        result.add(Community.withId(4, "나무 심기 봉사"));
        result.add(Community.withId(5, "낚시"));
        result.add(Community.withId(6, "등산"));
        result.add(Community.withId(7, "뜨개질"));
        result.add(Community.withId(8, "러닝"));
        result.add(Community.withId(9, "레고 조립"));
        result.add(Community.withId(10, "마술"));
        return result;
    }

    @Override
    public List<Community> readPopularCommunities() {
        ArrayList<Community> result = new ArrayList<>();
        result.add(Community.withId(35, "코딩"));
        result.add(Community.withId(36, "크로스핏"));
        result.add(Community.withId(37, "클라이밍"));
        result.add(Community.withId(38, "타로"));
        result.add(Community.withId(39, "테니스"));
        result.add(Community.withId(40, "풋살"));
        result.add(Community.withId(41, "필라테스"));
        result.add(Community.withId(42, "홈브루"));
        result.add(Community.withId(43, "홈카페"));
        return result;
    }

    @Override
    public Community readPopulistCommunity() {
        return null;
    }
}
