package com.jstp.configclient2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
public class Application {

    @Value("${cnj.message}")
    private String value;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @GetMapping("/read")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok(value);
    }
}


