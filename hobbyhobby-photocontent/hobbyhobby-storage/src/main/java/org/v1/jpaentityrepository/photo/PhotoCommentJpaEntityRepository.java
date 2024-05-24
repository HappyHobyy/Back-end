package org.v1.jpaentityrepository.photo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.v1.jpaentity.photo.PhotoArticleJpaEntity;
import org.v1.jpaentity.photo.PhotoCommentJpaEntity;
import org.v1.jpaentity.user.UserJpaEntity;
import org.v1.jparepository.photo.PhotoCommentJpaRepository;
import org.v1.model.comment.Comment;
import org.v1.repository.comment.PhotoCommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class PhotoCommentJpaEntityRepository implements PhotoCommentRepository {
    private final PhotoCommentJpaRepository repository;
    @Override
    public List<Comment> readComments(Long articleId) {
        List<PhotoCommentJpaEntity> jpaEntities = repository.readPhotoCommentJpaEntitiesByPhotoContent_Id(articleId);
        return jpaEntities.stream()
                .map(PhotoCommentJpaEntity::to)
                .collect(Collectors.toList());
    }

    @Override
    public long appendComment(Comment comment, Long articleId) {
        PhotoCommentJpaEntity jpaEntity = PhotoCommentJpaEntity.of(
                comment,
                PhotoArticleJpaEntity.onlyWithId(articleId),
                UserJpaEntity.onlyWithId(comment.getUser().id())
        );
        PhotoCommentJpaEntity savedEntity = repository.save(jpaEntity);
        return savedEntity.getId();
    }

    @Override
    public void removeComment(Long commentId) {
        repository.deleteById(commentId);
    }
}
