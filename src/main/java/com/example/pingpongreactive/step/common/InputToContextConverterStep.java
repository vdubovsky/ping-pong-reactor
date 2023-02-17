package com.example.pingpongreactive.step.common;

import com.example.pingpongreactive.model.ExecutionContext;

import java.util.function.Function;

public abstract class InputToContextConverterStep<INPUT, OUTPUT> implements Function<INPUT, ExecutionContext<INPUT,OUTPUT>> {
    @Override
    public ExecutionContext<INPUT, OUTPUT> apply(INPUT input) {
        ExecutionContext<INPUT, OUTPUT> executionContext = new ExecutionContext<>();
        executionContext.setInput(input);

        return executionContext;
    }
}
