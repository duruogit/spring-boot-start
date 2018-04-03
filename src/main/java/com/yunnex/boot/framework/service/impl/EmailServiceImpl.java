package com.yunnex.boot.framework.service.impl;

import javax.annotation.Resource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.yunnex.boot.framework.service.EmailServiceFacade;

@Service
public class EmailServiceImpl implements EmailServiceFacade {
	
	@Resource(name = "mailSender")  
    private JavaMailSenderImpl mailSender; 
	
	@Override
	public void sendSimpleMail(String sendTo, String titel, String content) {
		SimpleMailMessage message = new SimpleMailMessage(); 
		message.setFrom("w471949989@qq.com");  
        message.setTo(sendTo);  
        message.setSubject(titel);  
        message.setText(content);  
        mailSender.send(message);
	}

}
