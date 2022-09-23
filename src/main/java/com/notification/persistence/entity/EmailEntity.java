package com.notification.persistence.entity;

import com.notification.persistence.enums.StatusEmail;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@Table(name="TB_EMAIL")
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UUID;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private Date sendEmailDate;
    private StatusEmail statusEmail;

    public EmailEntity(Long UUID, String ownerRef, String emailFrom, String emailTo, String subject, String text, Date sendEmailDate, StatusEmail statusEmail) {
        this.UUID = UUID;
        this.ownerRef = ownerRef;
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.subject = subject;
        this.text = text;
        this.sendEmailDate = sendEmailDate;
        this.statusEmail = statusEmail;
    }

    public EmailEntity() {

    }
}
