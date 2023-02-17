package com.example.pingpongreactive.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PingPongResponseBO {

    private String player;
    private Integer retryCount;
}
