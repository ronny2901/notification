package com.notification.consumer;

import com.notification.api.request.EmailRequest;
import com.notification.persistence.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class EmailConsumer {

    final EmailService service;

    final ModelMapper mapper;

    @RabbitListener(queues="${spring.rabbitmq.queue")
    public void listen(@Payload EmailRequest request) {
        var response = service.send(request);
        log.info("stage=send-email-status-info, msg={}", response.getStatusEmail().toString());
    }

}
