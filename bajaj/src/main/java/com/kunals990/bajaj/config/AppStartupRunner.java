package com.kunals990.bajaj.config;

import com.kunals990.bajaj.service.WebhookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private  WebhookService webhookService;


    @Override
    public void run(ApplicationArguments args) {
        try {
            webhookService.executeChallenge();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
