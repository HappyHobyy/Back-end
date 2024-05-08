package org.v1.implementation;

import lombok.RequiredArgsConstructor;
import org.v1.handler.PhotoContentHandler;
import org.v1.handler.TextContentHandler;
import org.v1.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRemover {
    private final UserRepository userRepository;
    private final PhotoContentHandler photoContentHandler;
    private final TextContentHandler textContentHandler;
    public void remove(final Long userId) {
        userRepository.remove(userId);
        photoContentHandler.sendUserDelete(userId);
        textContentHandler.sendUserDelete(userId);
    }
}
