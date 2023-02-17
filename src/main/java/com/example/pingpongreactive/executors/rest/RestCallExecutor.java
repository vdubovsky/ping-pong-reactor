package com.example.pingpongreactive.executors.rest;

import com.example.pingpongreactive.model.ActionBO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RestCallExecutor {

    private final RestTemplate restTemplate;

    public ActionBO callPingPongServer(ActionBO actionBO) {
        HttpEntity<ActionBO> request = new HttpEntity<>(actionBO);
        return restTemplate.postForObject("http://localhost:9999/api/ping-pong-server", request, ActionBO.class);
    }
}
