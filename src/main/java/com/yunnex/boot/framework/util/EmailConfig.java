package com.yunnex.boot.framework.util;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailConfig {
	
	@Value("${stmp.host}")
    private String host;
	@Value("${stmp.port}")
    private String port;
    @Value("${stmp.account}")
    private String account;
    @Value("${stmp.password}")
    private String password;

    @Value("${mail.smtp.auth}")
    private String isAuth;
    @Value("${mail.smtp.timeout}")
    private String outTime;

    
    /**
     * <p>Title: getMailSender<／p>
     * <p>邮件发送类<／p>
     * @return
     */
    @Bean(name = "mailSender")
    public JavaMailSenderImpl getMailSender(){
        JavaMailSenderImpl javaMailSender= new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(Integer.valueOf(port));
        javaMailSender.setUsername(account);
        javaMailSender.setPassword(password);
        Properties properties=new Properties();
        properties.put("mail.smtp.auth", isAuth);
        properties.put("mail.smtp.timeout", outTime);
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }
    
}
