package com.example.pingpongreactive.step.pingpong;

import com.example.pingpongreactive.model.ActionBO;
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
public class PingPongFlowProcessorResolvePlay extends FlowAsyncProcessorStep<PingPongRequestBO, PingPongResponseBO> {

    private final PingPongFlowProcessorRetryCount retryCount;
    private final PingPongFlowProcessorOutputFill outputFill;

    public PingPongFlowProcessorResolvePlay(@Lazy PingPongFlowProcessorRetryCount retryCount, @Lazy PingPongFlowProcessorOutputFill outputFill) {
        this.retryCount = retryCount;
        this.outputFill = outputFill;
    }

    @Override
    public Mono<ExecutionContext<PingPongRequestBO, PingPongResponseBO>> apply(ExecutionContext<PingPongRequestBO, PingPongResponseBO> context) {
        log.debug("Thread: " + Thread.currentThread() + " " + getClass().getName());

        ActionBO actionBO = (ActionBO) context.getVars().get("externalResponse");
        if (actionBO.getAction().equals("ping")) {
            return Mono.just(context).flatMap(outputFill);
        } else {
            return Mono.just(context).flatMap(retryCount);
        }
    }
}
