package com.batch.report.sender.client.helper;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Getter
public class EmailProperties {
    @Value("${spring.mail.email}")
    private String sender;

    @Value("${spring.mail.username}")
    private String senderName;

/*    @Value("${spring.mail.audiences}")
    private String[] audiences;*/

    public EmailProperties(){

    }
}
