package org.v1.rabbitmq;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.v1.repository.ContentRepository;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class ExternalPhotoContentHandler {
    private RabbitTemplate rabbitTemplate;

}
