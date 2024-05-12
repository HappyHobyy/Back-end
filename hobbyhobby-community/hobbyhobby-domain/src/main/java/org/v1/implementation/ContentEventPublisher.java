package org.v1.implementation;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.v1.model.ContentEvent;

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
    public void publishGroupResetEvent() {
        ContentEvent contentEvent = new ContentEvent(this,ContentEvent.EventType.GROUP);
        applicationEventPublisher.publishEvent(contentEvent);
    }
}