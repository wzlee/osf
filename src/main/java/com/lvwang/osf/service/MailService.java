package com.lvwang.osf.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service("mailService")
public class MailService {
	
	public static final String mailFrom = "opensharefreedom@aliyun.com";
	public static final String context = "http://localhost:8080/com.lvwang.osf/account/activation/";
	
    @Autowired
    private JavaMailSenderImpl mailSender;
    
    
    public void sendMail(String to, String subject, String body) {
    	MimeMessage mail = mailSender.createMimeMessage();	
    	try {
    		MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
			helper.setFrom(mailFrom);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText("<a href='"+context+body+"?email="+to+"'>激活链接</a>", true);
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    	
    	
//    	mail.setFrom(mailFrom);
//    	mail.setTo(to);
//    	mail.setSubject(subject);
//    	mail.setText(body);
//    	mailSender.send(mail);
    }
}
