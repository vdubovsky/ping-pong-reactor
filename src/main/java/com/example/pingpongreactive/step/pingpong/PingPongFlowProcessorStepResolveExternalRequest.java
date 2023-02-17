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
public class PingPongFlowProcessorStepResolveExternalRequest extends FlowAsyncProcessorStep<PingPongRequestBO, PingPongResponseBO> {

    private final PingPongFlowProcessorStepPlay pingPongFlowProcessorStepPlay;

    public PingPongFlowProcessorStepResolveExternalRequest(@Lazy PingPongFlowProcessorStepPlay pingPongFlowProcessorStepPlay) {
        this.pingPongFlowProcessorStepPlay = pingPongFlowProcessorStepPlay;
    }

    @Override
    public Mono<ExecutionContext<PingPongRequestBO, PingPongResponseBO>> apply(ExecutionContext<PingPongRequestBO, PingPongResponseBO> context) {
        log.debug("Thread: " + Thread.currentThread() + " " + getClass().getName());

        ActionBO action = new ActionBO().setAction((String) context.getVars().get("action"));
        context.getVars().put("externalRequest", action);

        return Mono.just(context).flatMap(pingPongFlowProcessorStepPlay);
    }
}
