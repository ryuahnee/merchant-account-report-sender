package com.batch.report.sender.client.mail;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public class MailSendController {
    private final MailSendService mailSendService;
    public MailSendController(MailSendService mailSendService) {
        this.mailSendService = mailSendService;
    }
    public void accountMailSend() throws MessagingException, UnsupportedEncodingException {
        String to = "ryuahneee@gmail.com";
        String subject = "[가맹점 계좌 변경]";
        String htmlContent = "<h1>안녕하세요!</h1><p>이것은 <b>HTML</b> 형식의 테스트 이메일입니다.</p>";

        mailSendService.sendHtmlMessage(to,subject,htmlContent);
    }

}
