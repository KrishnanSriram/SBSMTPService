package com.smtp.app.router;

import com.smtp.app.handler.SMTPServiceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class EmailServiceHandler {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(SMTPServiceHandler smtpServiceHandler) {
        RouterFunction<ServerResponse> route = RouterFunctions
                .route(POST("/sendemail").and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), smtpServiceHandler::sendEmail);
        return route;
    }
}
