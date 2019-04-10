package com.hacker.mail;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@EnableDiscoveryClient
@SpringBootApplication @Configuration @EnableAutoConfiguration @ComponentScan({"com.hacker.mail"})
public class MailerApplication implements ApplicationRunner {

  

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MailerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
    
    }
}
