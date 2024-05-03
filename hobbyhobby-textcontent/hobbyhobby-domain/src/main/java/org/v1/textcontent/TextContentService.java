package org.v1.textcontent;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TextContentService {
    private static TextContentReader textContentReader;
    public List<TextContent> getTenTextContent(Long communityId) {
        return textContentReader.readTenTextContent(communityId);
    }
}
