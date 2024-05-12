package org.v1.service;

import lombok.AllArgsConstructor;
import net.javacrumbs.shedlock.core.SchedulerLock;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.v1.implementaion.community.CommunityManager;
import org.v1.implementaion.photoarticle.PhotoArticleReader;
import org.v1.model.PhotoArticle;

import java.util.List;

@Component
@AllArgsConstructor
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class ScheduleService {

    private final CommunityManager communityManager;
    private final PhotoArticleReader photoArticleReader;
    @Scheduled(cron = "0 */5 * * * *",zone = "Asia/Seoul")
    @SchedulerLock(name = "communityHotArticles", lockAtMostForString = "PT30S", lockAtLeastForString = "PT30S")
    public void findPopularArticle() {
        Integer communityId = communityManager.readPopulistCommunity();
        List<PhotoArticle> popularCommunityHotArticles = photoArticleReader.readPopularCommunityHotArticle(communityId);
        List<PhotoArticle> notPopularCommunityHotArticles = photoArticleReader.readNotPopularCommunityHotArticle(communityId);
        communityManager.sendPopularCommunityArticle(popularCommunityHotArticles);
        communityManager.sendNotPopularCommunityArticle(notPopularCommunityHotArticles);
    }
    @Scheduled(cron = "0 0 12 * * *", zone = "Asia/Seoul")
    @SchedulerLock(name = "SchedulerLock", lockAtMostForString = "PT30S", lockAtLeastForString = "PT30S")
    public void resetCommunityLikes() {
        communityManager.resetCommunityLikes();
    }
}