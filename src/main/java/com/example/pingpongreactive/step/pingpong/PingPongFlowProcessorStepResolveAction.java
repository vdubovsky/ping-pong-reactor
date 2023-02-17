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
public class PingPongFlowProcessorStepResolveAction extends FlowAsyncProcessorStep<PingPongRequestBO, PingPongResponseBO> {

    private final PingPongFlowProcessorStepResolveExternalRequest pingPongFlowProcessorStepResolveExternalRequest;

    public PingPongFlowProcessorStepResolveAction(@Lazy PingPongFlowProcessorStepResolveExternalRequest pingPongFlowProcessorStepResolveExternalRequest) {
        this.pingPongFlowProcessorStepResolveExternalRequest = pingPongFlowProcessorStepResolveExternalRequest;
    }

    @Override
    public Mono<ExecutionContext<PingPongRequestBO, PingPongResponseBO>> apply(ExecutionContext<PingPongRequestBO, PingPongResponseBO> context) {
        log.debug("Thread: " + Thread.currentThread() + " " + getClass().getName());

        context.getVars().put("action", Math.random() > 0.5 ? "ping" : "pong");
        return Mono.just(context).flatMap(pingPongFlowProcessorStepResolveExternalRequest);
    }
}
