package com.example.threading.utils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Barrier {
    private final int totalThreads;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int waitingThreads = 0;

    public Barrier(int totalThreads) {
        this.totalThreads = totalThreads;
    }

    public void await() throws InterruptedException {
        lock.lock();
        try {
            waitingThreads++;
            if (waitingThreads < totalThreads) {
                condition.await();
            } else {
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}
