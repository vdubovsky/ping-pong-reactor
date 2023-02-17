package com.example.pingpongreactive.step.pingpong;

import com.example.pingpongreactive.model.ExecutionContext;
import com.example.pingpongreactive.model.PingPongRequestBO;
import com.example.pingpongreactive.model.PingPongResponseBO;
import com.example.pingpongreactive.step.common.FlowAsyncProcessorStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PingPongFlowProcessorRetryCount extends FlowAsyncProcessorStep<PingPongRequestBO, PingPongResponseBO> {

    private final PingPongFlowProcessorStepResolveAction resolveAction;

    public PingPongFlowProcessorRetryCount(@Lazy PingPongFlowProcessorStepResolveAction resolveAction) {
        this.resolveAction = resolveAction;
    }

    @Override
    public Mono<ExecutionContext<PingPongRequestBO, PingPongResponseBO>> apply(ExecutionContext<PingPongRequestBO, PingPongResponseBO> context) {
        log.debug("Thread: " + Thread.currentThread() + " " + getClass().getName());

        Integer retryCount = (Integer) context.getVars().getOrDefault("retryCount", 0);
        retryCount++;
        context.getVars().put("retryCount", retryCount);

        return Mono.just(context).flatMap(resolveAction);
    }
}
