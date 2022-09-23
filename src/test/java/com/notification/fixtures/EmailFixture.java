package com.notification.fixtures;

import com.github.javafaker.Faker;
import com.notification.api.request.EmailRequest;
import com.notification.persistence.entity.EmailEntity;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Locale;

public final class EmailFixture {

    public static final Faker FAKER = new Faker(new Locale("pt", "BR"));

    public static EmailRequest createEmailTemplateRequest(){
        return EmailRequest.builder()
                .emailFrom(FAKER.internet().emailAddress())
                .ownerRef("Charge")
                .emailTo(FAKER.internet().emailAddress())
                .subject("E-MAIL TITLE")
                .text("E-MAIL BODY")
                .build();
    }

    public static EmailEntity createEmailEntityTemplate(){
        return EmailEntity.builder()
                .emailFrom(FAKER.internet().emailAddress())
                .ownerRef("Charge")
                .emailTo(FAKER.internet().emailAddress())
                .subject("E-MAIL TITLE")
                .text("E-MAIL BODY")
                .build();
    }

    public static List<String> createRandomVariables() {
        return Lists.newArrayList(FAKER.esports().player(), FAKER.esports().team());
    }

}
