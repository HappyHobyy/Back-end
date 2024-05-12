package org.v1.implementation;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.v1.external.ExternalCommunitySender;
import org.v1.model.ContentEvent;

@Component
@AllArgsConstructor
public class ContentEventListener {

    private final ExternalCommunitySender communitySender;

    @EventListener
    public void handleLikeEvent(ContentEvent event) {
        if (event.getEventType() == ContentEvent.EventType.PHOTO) {
            communitySender.sendResetPhotoArticleCached(ContentEvent.EventType.PHOTO);
        } else {
            communitySender.sendResetGroupArticleCached(ContentEvent.EventType.GROUP);
        }
    }
}