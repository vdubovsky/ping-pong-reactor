package com.example.pingpongreactive.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class ExecutionContext<INPUT,OUTPUT> {

    private INPUT input;
    private OUTPUT output;

    private Map<String, Object> vars = new HashMap<>();
}
