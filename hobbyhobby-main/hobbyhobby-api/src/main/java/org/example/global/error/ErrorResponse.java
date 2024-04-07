package org.example.global.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.error.ErrorCode;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private final String error;

    private final String code;

    private final String message;

    public static ErrorResponse from(final ErrorCode errorCode) {
        return new ErrorResponse(
                errorCode.name(),
                errorCode.getCode(),
                errorCode.getMessage()
        );
    }

}