package org.example.error;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
