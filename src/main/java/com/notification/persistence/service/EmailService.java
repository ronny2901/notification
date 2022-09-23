package com.notification.persistence.service;

import com.notification.api.request.EmailRequest;
import com.notification.exception.BadRequestException;
import com.notification.persistence.entity.EmailEntity;
import com.notification.persistence.enums.StatusEmail;
import com.notification.persistence.repository.EmailRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailService {
    final EmailRepository repository;
    final ModelMapper modelMapper;
    final JavaMailSender sender;
    public EmailEntity send(final EmailRequest request) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(request.getEmailFrom());
            message.setTo(request.getEmailTo());
            message.setSubject(request.getSubject());
            message.setText(request.getText());
            sender.send(message);
        } catch (MailException e) {
            var entityPersisted = repository.save(modelMapper.map(request, EmailEntity.class));
            entityPersisted.setSendEmailDate(new Date());
            entityPersisted.setStatusEmail(StatusEmail.ERROR);
            String msgError = e.getCause().getCause().getMessage();
            log.error("stage=send-email-error, msg={}", msgError, e);
            repository.save(entityPersisted);
            throw new BadRequestException(msgError, e);
        } finally {
            var entityPersisted = repository.save(modelMapper.map(request, EmailEntity.class));
            entityPersisted.setSendEmailDate(new Date());
            entityPersisted.setStatusEmail(StatusEmail.SENT);
            log.info("stage=send-email-success, entity={}", entityPersisted);
            repository.save(entityPersisted);
            return entityPersisted;
        }
    }
}
