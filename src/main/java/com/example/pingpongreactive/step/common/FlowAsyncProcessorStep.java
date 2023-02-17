package com.example.pingpongreactive.step.common;

import com.example.pingpongreactive.model.ExecutionContext;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public abstract class FlowAsyncProcessorStep<INPUT, OUTPUT> implements Function<ExecutionContext<INPUT, OUTPUT>, Mono<ExecutionContext<INPUT, OUTPUT>>> {
}
