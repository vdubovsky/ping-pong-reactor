package com.example.pingpongreactive.service;

import com.example.pingpongreactive.model.PingPongRequestBO;
import com.example.pingpongreactive.model.PingPongResponseBO;
import com.example.pingpongreactive.step.pingpong.PingPongContextToOutputConverterStep;
import com.example.pingpongreactive.step.pingpong.PingPongFlowProcessorStep;
import com.example.pingpongreactive.step.pingpong.PingPongInputToContextConverterStep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PingPongServiceImpl implements PingPongService {

    private final PingPongInputToContextConverterStep inputToContextConverterStep;
    private final PingPongContextToOutputConverterStep contextToOutputConverterStep;
    private final PingPongFlowProcessorStep pingPongFlowProcessorStep;

    @Override
    public Mono<PingPongResponseBO> pingPong(Mono<PingPongRequestBO> request) {
        return request
                .mapNotNull(inputToContextConverterStep)
                .flatMap(pingPongFlowProcessorStep)
                .mapNotNull(contextToOutputConverterStep);
    }
}
