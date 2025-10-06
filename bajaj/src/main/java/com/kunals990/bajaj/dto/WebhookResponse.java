package com.kunals990.bajaj.dto;

import lombok.*;

public class WebhookResponse {
    private String webhook;
    private String accessToken;

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}