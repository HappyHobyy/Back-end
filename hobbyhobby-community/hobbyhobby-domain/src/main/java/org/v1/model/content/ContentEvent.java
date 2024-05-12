package org.v1.model.content;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
@Getter
public class ContentEvent extends ApplicationEvent {
    private final EventType eventType;

    public ContentEvent(Object source, EventType eventType) {
        super(source);
        this.eventType = eventType;
    }
    public enum EventType {
        PHOTO,
        GROUP
    }

}
