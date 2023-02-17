package com.example.pingpongreactive.step.pingpong;

import com.example.pingpongreactive.model.PingPongRequestBO;
import com.example.pingpongreactive.model.PingPongResponseBO;
import com.example.pingpongreactive.step.common.InputToContextConverterStep;
import org.springframework.stereotype.Component;

@Component
public class PingPongInputToContextConverterStep extends InputToContextConverterStep<PingPongRequestBO, PingPongResponseBO> {
}
