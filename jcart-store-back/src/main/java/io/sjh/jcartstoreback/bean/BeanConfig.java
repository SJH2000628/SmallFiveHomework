package io.sjh.jcartstoreback.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.security.SecureRandom;
import java.util.Properties;

@Configuration
public class BeanConfig {

    @Bean
    public SecureRandom secureRandom(){
        return new SecureRandom();
    }

}
