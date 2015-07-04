package com.lvwang.osf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


@Service("mailService")
public class MailService {
    @Autowired
    private MailSender mailSender;
    
    public void sendMail(String to, String subject, String body) {
    	SimpleMailMessage mail = new SimpleMailMessage();
    	mail.setFrom("opensharefreedom@aliyun.com");
    	mail.setTo(to);
    	mail.setSubject(subject);
    	mail.setText(body);
    	mailSender.send(mail);
    }
}
