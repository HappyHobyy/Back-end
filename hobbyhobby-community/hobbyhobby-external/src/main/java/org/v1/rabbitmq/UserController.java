package org.v1.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import response.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final ExternalTextContentHandler externalTextContentHandler;

    @PostMapping("/test")
    public HttpResponse<Object> changeProfileImage(
            @RequestHeader("userId") Long userId
    ) {
        CompletableFuture<MessageDto> future = externalTextContentHandler.sendMessage(new MessageDto(userId, "test"));
        MessageDto message = future.join();
        return HttpResponse.success(message);
    }
}
