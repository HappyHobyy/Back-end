package org.v1.dto;

import org.v1.model.content.Contents;

import java.util.List;
import java.util.stream.Collectors;
public record ContentResponse(
         PhotoArticlesDTO photoArticles,
         GatheringArticlesDTO GatheringArticles
) {
    public static ContentResponse ofContents(Contents contents) {
        return new ContentResponse(
                PhotoArticlesDTO.fromPhotoArticles(contents.getPhotoArticles()),
                GatheringArticlesDTO.fromGatheringArticles(contents.getGatheringArticles())
        );
    }
    record PhotoArticlesDTO(
            List<PhotoArticleResponse> popularPhotoArticle,
            List<PhotoArticleResponse> nonePopularPhotoArticle
    ) {
        static PhotoArticlesDTO fromPhotoArticles(Contents.PhotoArticles photoArticles) {
            return new PhotoArticlesDTO(
                    photoArticles.popularPhotoArticle().stream()
                            .map(PhotoArticleResponse::fromPhotoArticle)
                            .collect(Collectors.toList()),
                    photoArticles.nonePopularPhotoArticle().stream()
                            .map(PhotoArticleResponse::fromPhotoArticle)
                            .collect(Collectors.toList())
            );
        }
    }

    record GatheringArticlesDTO(
            List<GatheringArticleResponse> popularGatheringArticle,
            List<GatheringArticleResponse> nonePopularGatheringArticle
    ) {
        static GatheringArticlesDTO fromGatheringArticles(Contents.GatheringArticles gatheringArticles) {
            return new GatheringArticlesDTO(
                    gatheringArticles.popularGatheringArticle().stream()
                            .map(GatheringArticleResponse::fromGatheringArticle)
                            .collect(Collectors.toList()),
                    gatheringArticles.nonePopularGatheringArticle().stream()
                            .map(GatheringArticleResponse::fromGatheringArticle)
                            .collect(Collectors.toList())
            );
        }
    }
}