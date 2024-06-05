package com.jtsp.configclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final Environment environment;


    @Autowired
    public MyController(Environment environment) {
        this.environment = environment;
    }

    @EventListener({RefreshScopeRefreshedEvent.class, ApplicationReadyEvent.class})
    public void onEvent() {
        System.out.println(environment.getProperty("cnj.message"));
    }

    @GetMapping("/read")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok(environment.getProperty("cnj.message"));
    }
}
