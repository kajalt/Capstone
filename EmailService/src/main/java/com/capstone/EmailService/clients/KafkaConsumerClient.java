package com.capstone.EmailService.clients;

import com.capstone.EmailService.dtos.EmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

@Service
public class KafkaConsumerClient {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmailUtil emailUtil;

    @KafkaListener(topics = "sendEmail",groupId = "emailService")
    public void handleSendEmail(String message) throws JsonProcessingException {
        EmailDto emailDto = objectMapper.readValue(message, EmailDto.class);
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailDto.getFrom(), "ptykogfbncyuyggj");
            }
        };
        Session session = Session.getInstance(props, auth);

        emailUtil.sendEmail(session, emailDto.getTo(), emailDto.getSubject(), emailDto.getBody());

    }
}