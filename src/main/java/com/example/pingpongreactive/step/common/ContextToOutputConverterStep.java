package com.example.pingpongreactive.step.common;

import com.example.pingpongreactive.model.ExecutionContext;

import java.util.function.Function;

public abstract class ContextToOutputConverterStep<INPUT, OUTPUT> implements Function<ExecutionContext<INPUT, OUTPUT>, OUTPUT> {

    @Override
    public OUTPUT apply(ExecutionContext<INPUT, OUTPUT> executionContext) {
        return executionContext.getOutput();
    }
}
