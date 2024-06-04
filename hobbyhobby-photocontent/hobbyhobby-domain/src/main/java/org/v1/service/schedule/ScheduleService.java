package org.v1.service.schedule;

import lombok.AllArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.v1.implementaion.community.CommunityManager;
import org.v1.implementaion.article.GatheringArticleReader;
import org.v1.implementaion.article.PhotoArticleReader;
import org.v1.model.article.GatheringArticle;
import org.v1.model.article.PhotoArticle;

import java.util.List;

@Component
@AllArgsConstructor
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class ScheduleService {

    private final CommunityManager communityManager;
    private final PhotoArticleReader photoArticleReader;
    private final GatheringArticleReader gatheringArticleReader;
    @Scheduled(cron = "0 */1 * * * *",zone = "Asia/Seoul")
    @SchedulerLock(name = "communityHotPhotoArticles")
    public void findPopularPhotoArticle() {
        int communityId = communityManager.readPopulistCommunity();
        List<PhotoArticle> popularCommunityHotArticles = photoArticleReader.readPopularCommunityHotArticle(communityId);
        List<PhotoArticle> notPopularCommunityHotArticles = photoArticleReader.readNotPopularCommunityHotArticle(communityId);
        communityManager.sendPopularPhotoArticle(popularCommunityHotArticles,notPopularCommunityHotArticles);
    }
    @Scheduled(cron = "0 */1 * * * *",zone = "Asia/Seoul")
    @SchedulerLock(name = "communityHotGatheringArticles")
    public void findPopularGatheringArticle() {
        int communityId = communityManager.readPopulistCommunity();
        List<GatheringArticle> popularCommunityHotArticles = gatheringArticleReader.readPopularCommunityHotArticle(communityId);
        List<GatheringArticle> notPopularCommunityHotArticles = gatheringArticleReader.readNotPopularCommunityHotArticle(communityId);
        communityManager.sendPopularGatheringArticle(popularCommunityHotArticles,notPopularCommunityHotArticles);
    }
    @Scheduled(cron = "0 0 12 * * *", zone = "Asia/Seoul")
    @SchedulerLock(name = "communityHotReset")
    public void resetCommunityLikes() {
        communityManager.resetCommunityLikes();
    }
}