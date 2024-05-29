package org.v1.jpaentityrepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.v1.jpaentity.CommunityJpaEntity;
import org.v1.jpaentity.LikedCommunityJpaEntity;
import org.v1.jparepository.LikeCommunityJpaRepository;
import org.v1.model.community.Community;
import org.v1.model.like.Like;
import org.v1.repository.CommunityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CommunityJpaEntityRepository implements CommunityRepository {
    private final LikeCommunityJpaRepository likeCommunityJpaRepository;

    @Override
    public List<Community> readUserCommunities(Long userId) {
        likeCommunityJpaRepository.findAllByUser_Id(userId);
        List<LikedCommunityJpaEntity> communities = likeCommunityJpaRepository.findAllByUser_Id(userId);
        return communities.stream().map(LikedCommunityJpaEntity::to).collect(Collectors.toList());
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
    public List<Community> readRecommendCommunities(Long userId) {
        ArrayList<Community> result = new ArrayList<>();
        result.add(Community.withId(13, "반려식물"));
        result.add(Community.withId(14, "배드민턴"));
        result.add(Community.withId(15, "베이킹"));
        result.add(Community.withId(16, "보드게임"));
        result.add(Community.withId(17, "볼링"));
        result.add(Community.withId(18, "비디오게임"));
        result.add(Community.withId(19, "사진"));
        result.add(Community.withId(20, "산책"));
        result.add(Community.withId(21, "서핑"));
        result.add(Community.withId(22, "스케이트 보드"));
        result.add(Community.withId(23, "스키"));
        result.add(Community.withId(24, "여행"));
        return result;
    }

    @Override
    public Community readPopulistCommunity() {
        return null;
    }

    @Override
    public void updateCommunityLike(Like like) {

    }
}
