package com.example.pingpongreactive.service;

import com.example.pingpongreactive.model.PingPongRequestBO;
import com.example.pingpongreactive.model.PingPongResponseBO;
import reactor.core.publisher.Mono;

public interface PingPongService {
    Mono<PingPongResponseBO> pingPong(Mono<PingPongRequestBO> request);
}
