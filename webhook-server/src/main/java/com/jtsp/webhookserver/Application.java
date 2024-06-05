package com.jtsp.webhookserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RefreshScope
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/webhook")
    public String receiveWebhook(@RequestBody String payload) {

        // Create HttpHeaders and set the media type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Set the desired media type here
        HttpEntity<Void> entity = new HttpEntity<>(null, headers);
        // Make a POST request with the desired media type
        String url = "http://localhost:8088/actuator/busrefresh"; // Replace with the actual endpoint URL
        restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);
        return "Webhook received";
    }
}
