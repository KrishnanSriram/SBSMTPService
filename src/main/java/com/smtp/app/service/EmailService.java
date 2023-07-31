package com.smtp.app.service;

import com.smtp.app.domain.EmailRequest;
import com.smtp.app.exception.EmailServiceException;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public Mono<Void> sendEMail(EmailRequest emailRequest) {
        logger.info(emailRequest.toString());
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(emailRequest.getTo());
            simpleMailMessage.setSubject(emailRequest.getSubject());
            simpleMailMessage.setFrom(emailRequest.getFrom());
            simpleMailMessage.setText(emailRequest.getBody());
            return Mono.fromRunnable(() -> javaMailSender.send(simpleMailMessage));
        } catch (Exception ex) {
            logger.info("Failed to send email");
            logger.info(ex.getMessage());
            throw new EmailServiceException(ex.getMessage());
        }
    }
}
