package com.example.pingpongreactive.rest;

import com.example.pingpongreactive.model.PingPongRequestBO;
import com.example.pingpongreactive.model.PingPongResponseBO;
import com.example.pingpongreactive.service.PingPongService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ping-pong")
@Slf4j
public class PingPongController {

    private final PingPongService pingPongService;

    @PostMapping
    public Mono<PingPongResponseBO> pingPong(@RequestBody Mono<PingPongRequestBO> request){
        return Mono.defer(()-> pingPongService.pingPong(request));
    }
}
