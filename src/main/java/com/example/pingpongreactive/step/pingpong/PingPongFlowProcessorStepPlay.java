package com.example.pingpongreactive.step.pingpong;

import com.example.pingpongreactive.executors.rest.RestCallExecutor;
import com.example.pingpongreactive.model.ActionBO;
import com.example.pingpongreactive.model.ExecutionContext;
import com.example.pingpongreactive.model.PingPongRequestBO;
import com.example.pingpongreactive.model.PingPongResponseBO;
import com.example.pingpongreactive.step.common.FlowAsyncProcessorStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ExecutorService;

@Service
@Slf4j
public class PingPongFlowProcessorStepPlay extends FlowAsyncProcessorStep<PingPongRequestBO, PingPongResponseBO> {

    private final ExecutorService executorService;
    private final RestCallExecutor restCallExecutor;
    private final PingPongFlowProcessorResolvePlay resolvePlay;

    public PingPongFlowProcessorStepPlay(@Qualifier("blockingIoThreadPool") ExecutorService executorService,
                                         RestCallExecutor restCallExecutor,
                                         @Lazy PingPongFlowProcessorResolvePlay resolvePlay) {
        this.executorService = executorService;
        this.restCallExecutor = restCallExecutor;
        this.resolvePlay = resolvePlay;
    }

    @Override
    public Mono<ExecutionContext<PingPongRequestBO, PingPongResponseBO>> apply(ExecutionContext<PingPongRequestBO, PingPongResponseBO> context) {
        log.debug("Thread: " + Thread.currentThread() + " " + getClass().getName());

        ActionBO actionBO = (ActionBO) context.getVars().get("externalRequest");

        return Mono
                .fromCallable(() -> restCallExecutor.callPingPongServer(actionBO))
                .subscribeOn(Schedulers.fromExecutor(executorService))
                .map(a -> {
                    context.getVars().put("externalResponse", a);
                    return context;
                })
                .flatMap(resolvePlay);
    }
}
