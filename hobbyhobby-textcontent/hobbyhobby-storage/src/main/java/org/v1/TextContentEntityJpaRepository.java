package org.v1;

import org.springframework.stereotype.Repository;
import org.v1.textcontent.TextContent;
import org.v1.textcontent.TextContentRepository;

import java.util.List;

@Repository
public class TextContentEntityJpaRepository implements TextContentRepository {

    @Override
    public List<TextContent> getTextContentByCommunity(Long communityId) {
        return null;
    }
    @Override
    public void saveTextContent(Community community) {

    }
}
