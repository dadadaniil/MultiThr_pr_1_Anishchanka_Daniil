package com.example.threading.states;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ActiveState implements ResourceState {
    @Override
    public void handle(String threadName) {
        log.info("{}: Resource is active.", threadName);
    }
}
