package com.smtp.app.domain;

import lombok.*;

@Data
@AllArgsConstructor
@ToString

//@NoArgsConstructor
public class EmailRequest {
    @NonNull
    private String to;
    @NonNull
    private String from;
    @NonNull
    private String subject;
    @NonNull
    private String body;
}
