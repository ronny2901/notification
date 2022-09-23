package com.notification.api.controller;

import com.notification.api.request.EmailRequest;
import com.notification.persistence.entity.EmailEntity;
import com.notification.persistence.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(EmailController.RESOURCE_PATH_EMAIL)
@RequiredArgsConstructor
public class EmailController {

    public static final String RESOURCE_PATH_EMAIL = "/email";
    private final EmailService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmailEntity> sendEmail(@RequestBody @Validated final EmailRequest request) {
        var responseData = service.send(request);
        return ResponseEntity.ok().body(responseData);
    }
}
