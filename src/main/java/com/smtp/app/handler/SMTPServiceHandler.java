package com.smtp.app.handler;

import com.smtp.app.domain.EmailRequest;
import com.smtp.app.exception.EmailServiceException;
import com.smtp.app.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SMTPServiceHandler {
    @Autowired
    private EmailService emailService;

    private Logger logger = LoggerFactory.getLogger(SMTPServiceHandler.class);

    public Mono<ServerResponse> sendEmail(ServerRequest serverRequest) {
        logger.info("sendEmail invoked");
        return serverRequest.bodyToMono(EmailRequest.class)
                .flatMap(emailRequest -> emailService.sendEMail(emailRequest))
                .then(ServerResponse.ok().body(Mono.just("Email sent successfully"), String.class))
                .onErrorResume(EmailServiceException.class, e ->
                        ServerResponse.badRequest().syncBody(e.getMessage())
                ).onErrorResume(
                        e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .syncBody(e.getMessage())
                );
    }
}
