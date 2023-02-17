package com.example.pingpongreactive.step.pingpong;

import com.example.pingpongreactive.model.ActionBO;
import com.example.pingpongreactive.model.ExecutionContext;
import com.example.pingpongreactive.model.PingPongRequestBO;
import com.example.pingpongreactive.model.PingPongResponseBO;
import com.example.pingpongreactive.step.common.FlowAsyncProcessorStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PingPongFlowProcessorOutputFill extends FlowAsyncProcessorStep<PingPongRequestBO, PingPongResponseBO> {

    @Override
    public Mono<ExecutionContext<PingPongRequestBO, PingPongResponseBO>> apply(ExecutionContext<PingPongRequestBO, PingPongResponseBO> context) {
        log.debug("Thread: " + Thread.currentThread() + " " + getClass().getName());

        Integer retryCount = (Integer) context.getVars().getOrDefault("retryCount", 0);
        PingPongResponseBO pingPongResponseBO = new PingPongResponseBO().setRetryCount(retryCount).setPlayer(context.getInput().getPlayer());
        context.setOutput(pingPongResponseBO);

        return Mono.just(context);
    }
}
