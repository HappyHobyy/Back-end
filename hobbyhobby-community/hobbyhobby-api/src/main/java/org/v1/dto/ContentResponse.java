package org.v1.dto;

import lombok.AllArgsConstructor;
import org.v1.model.content.Contents;
import org.v1.model.content.GroupArticle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
public record ContentResponse(
         PhotoArticlesDTO photoArticles,
         GroupArticlesDTO groupArticles
) {
    public static ContentResponse ofContents(Contents contents) {
        return new ContentResponse(
                PhotoArticlesDTO.fromPhotoArticles(contents.getPhotoArticles()),
                GroupArticlesDTO.fromGroupArticles(contents.getGroupArticles())
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

    record GroupArticlesDTO(
            List<GroupArticleResponse> popularGroupArticle,
            List<GroupArticleResponse> nonePopularGroupArticle
    ) {
        static GroupArticlesDTO fromGroupArticles(Contents.GroupArticles groupArticles) {
            return new GroupArticlesDTO(
                    groupArticles.popularGroupArticle().stream()
                            .map(GroupArticleResponse::fromGroupArticle)
                            .collect(Collectors.toList()),
                    groupArticles.nonePopularGroupArticle().stream()
                            .map(GroupArticleResponse::fromGroupArticle)
                            .collect(Collectors.toList())
            );
        }
    }
}