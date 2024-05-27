package org.v1.implementation.content;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.v1.model.content.ContentEvent;

@Component
public class ContentEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public ContentEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishPhotoArticleResetEvent() {
        ContentEvent contentEvent = new ContentEvent(this,ContentEvent.EventType.PHOTO);
        applicationEventPublisher.publishEvent(contentEvent);
    }
    public void publishGatheringResetEvent() {
        ContentEvent contentEvent = new ContentEvent(this,ContentEvent.EventType.Gathering);
        applicationEventPublisher.publishEvent(contentEvent);
    }
}