package com.notification.service;

import com.notification.api.request.EmailRequest;
import com.notification.fixtures.EmailFixture;
import com.notification.persistence.repository.EmailRepository;
import com.notification.persistence.service.EmailService;
import lombok.var;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("EmailTemplate Tests")
public class EmailServiceTest {

    @InjectMocks
    private EmailService service;

    @Mock
    MailException exception;

    @Mock
    JavaMailSender sender;

    @Mock
    private EmailRepository repository;
    @Spy
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Test
    @DisplayName("tests send e-mail error")
    void shouldThrowExceptionTryingSendEmail() {
        var emailRequest = EmailFixture.createEmailTemplateRequest();

        assertThrows(NullPointerException.class, ()-> service.send(emailRequest));

    }
}
