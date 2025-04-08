package com.batch.report.sender.client.mail;

import com.batch.report.sender.client.helper.EmailProperties;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class MailSendService {

    private final JavaMailSender javaMailSender;
    private final EmailProperties emailProperties;

    public MailSendService(JavaMailSender javaMailSender, EmailProperties emailProperties) {
        this.javaMailSender = javaMailSender;
        this.emailProperties = emailProperties;
    }

    public String sendHtmlMessage(String to, String subject, String htmlContent) throws UnsupportedEncodingException {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            helper.setFrom(emailProperties.getSender(),emailProperties.getSenderName());
            javaMailSender.send(mimeMessage);

            return "메일발송 성공";
        } catch (MessagingException e) {
            throw new RuntimeException("메일 발송 오류: "+e);
        }

    }
}
