package org.v1.domain.textcontent;

import org.v1.textcontent.TextContent;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record TextContentResponse(
        Long id,
        String title,
        LocalDateTime date,
        String category,
        String userName,
        Long userId,
        Integer likes
) {
    public static List<TextContentResponse> of(List<TextContent> textContentList) {
        if (textContentList == null) {
            return Collections.emptyList();
        }

        return textContentList.stream()
                .map(textContent -> new TextContentResponse(
                        textContent.getId(),
                        textContent.getTitle(),
                        textContent.getDate(),
                        textContent.getCategory(),
                        textContent.getUser().nickname(),
                        textContent.getUser().userId(),
                        textContent.getLikes()
                ))
                .collect(Collectors.toList());
    }
}
