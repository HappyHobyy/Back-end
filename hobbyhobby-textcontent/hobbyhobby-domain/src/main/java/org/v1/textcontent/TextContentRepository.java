package org.v1.textcontent;

import org.v1.Community;

import java.util.List;

public interface TextContentRepository {
    List<TextContent> getTextContentByCommunity(Long CommunityId);
    void saveTextContent(Community community);
}
