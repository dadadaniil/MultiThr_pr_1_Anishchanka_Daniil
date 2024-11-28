package com.example.threading.utils;

import java.util.concurrent.Semaphore;

public class CustomSemaphore {
    private final Semaphore semaphore;

    public CustomSemaphore(int permits) {
        this.semaphore = new Semaphore(permits);
    }

    public void acquire() throws InterruptedException {
        semaphore.acquire();
    }

    public void release() {
        semaphore.release();
    }
}
