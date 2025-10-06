package com.kunals990.bajaj.service;

import com.kunals990.bajaj.dto.SolutionRequest;
import com.kunals990.bajaj.dto.WebhookRequest;
import com.kunals990.bajaj.dto.WebhookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WebhookService {

    private final RestTemplate restTemplate;
    private final SqlService sqlService;



    private static final String GENERATE_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

    public void executeChallenge() {
        WebhookResponse webhookResponse = generateWebhook();

        if (webhookResponse == null || webhookResponse.getWebhook() == null) {
            return;
        }
        String sqlQuery = sqlService.getSolution();

        submitSolution(webhookResponse.getWebhook(), webhookResponse.getAccessToken(), sqlQuery);
    }

    private WebhookResponse generateWebhook() {
        try {
            WebhookRequest request = new WebhookRequest();

            request.setName("Ridhesh Chauhan");
            request.setRegNo("112215050");
            request.setEmail("112215050@cse.iiitp.ac.in");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<WebhookResponse> response = restTemplate.exchange(
                    GENERATE_WEBHOOK_URL,
                    HttpMethod.POST,
                    entity,
                    WebhookResponse.class
            );

            return response.getBody();

        } catch (Exception e) {
            return null;
        }
    }

    private void submitSolution(String webhookUrl, String accessToken, String sqlQuery) {
        try {
            SolutionRequest request = new SolutionRequest();
            request.setFinalQuery(sqlQuery);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", accessToken);

            HttpEntity<SolutionRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    webhookUrl,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            System.out.println(response);


        } catch (Exception e) {
            System.out.println("error is "+e);
        }
    }
}