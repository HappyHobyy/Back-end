package org.v1.jparepository;

import org.springframework.stereotype.Repository;
import org.v1.model.TextComment;
import org.v1.repository.TextCommentRepository;

import java.util.List;

@Repository
public class TextCommentJpaEntityRepository implements TextCommentRepository {
    @Override
    public List<TextComment> readComments(Long articleId) {
        return null;
    }

    @Override
    public Long appendComment(TextComment textComment, Long articleId) {
        return null;
    }

    @Override
    public void removeComment(Long commentId) {

    }
}
