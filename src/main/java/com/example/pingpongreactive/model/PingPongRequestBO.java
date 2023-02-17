package com.example.pingpongreactive.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PingPongRequestBO {

    private String player;
}
