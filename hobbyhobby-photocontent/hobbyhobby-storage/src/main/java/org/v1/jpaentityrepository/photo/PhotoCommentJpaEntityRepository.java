package org.v1.jpaentityrepository.photo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.v1.jpaentity.photo.PhotoCommentJpaEntity;
import org.v1.jparepository.photo.PhotoArticleJpaRepository;
import org.v1.jparepository.photo.PhotoCommentJpaRepository;
import org.v1.model.comment.Comment;
import org.v1.repository.comment.PhotoCommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class PhotoCommentJpaEntityRepository implements PhotoCommentRepository {
    private final PhotoCommentJpaRepository photoCommentJpaRepository;
    private final PhotoArticleJpaRepository photoArticleJpaRepository;
    @Override
    public List<Comment> readComments(Long articleId) {
        List<PhotoCommentJpaEntity> jpaEntities = photoCommentJpaRepository.findByPhotoContent_Id(articleId);
        return jpaEntities.stream()
                .map(PhotoCommentJpaEntity::to)
                .collect(Collectors.toList());
    }

    @Override
    public long appendComment(Comment comment, Long articleId) {
        PhotoCommentJpaEntity jpaEntity = PhotoCommentJpaEntity.of(comment, articleId);
        PhotoCommentJpaEntity savedEntity = photoCommentJpaRepository.save(jpaEntity);
        photoArticleJpaRepository.incrementCommentsById(articleId);
        return savedEntity.getId();
    }

    @Override
    public void removeComment(Long commentId) {
        photoCommentJpaRepository.deleteById(commentId);
        photoArticleJpaRepository.decrementCommentsById(commentId);
    }
}
