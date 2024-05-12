package org.v1.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.v1.external.ExternalCommunitySender;
import org.v1.model.ContentEvent;
import org.v1.model.PhotoArticle;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class ExternalCommunityHandler implements ExternalCommunitySender {
    private final ExternalCommunityMessageSender messageHandler;
    @Override
    public void sendResetPhotoArticleCached(ContentEvent.EventType photo) {
        messageHandler.sendAllCommunity("photo");
    }
    @Override
    public void sendResetGroupArticleCached(ContentEvent.EventType group) {
        messageHandler.sendAllCommunity("group");
    }
}
