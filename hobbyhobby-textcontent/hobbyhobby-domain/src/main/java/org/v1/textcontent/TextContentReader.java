package org.v1.textcontent;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TextContentReader {
    private TextContentRepository textContentRepository;
    public List<TextContent> readTenTextContent(Long communityId) {
        return textContentRepository.getTextContentByCommunity(communityId);
    }
}
