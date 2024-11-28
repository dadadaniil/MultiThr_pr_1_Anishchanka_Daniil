package com.example.threading;

import com.example.threading.states.IdleState;
import com.example.threading.states.ResourceState;

public class Resource {
    private ResourceState currentState;

    public Resource() {
        this.currentState = new IdleState();
    }

    public void setState(ResourceState state) {
        this.currentState = state;
    }

    public void use(String threadName) {
        currentState.handle(threadName);
    }
}
