package org.v1.external;

import org.v1.model.content.ContentEvent;

public interface ExternalCommunitySender {
    void sendResetPhotoArticleCached(ContentEvent.EventType photo);
    void sendResetGatheringArticleCached(ContentEvent.EventType Gathering);
}
