package com.example.threading;

import com.example.threading.states.ActiveState;
import com.example.threading.utils.Barrier;
import com.example.threading.utils.CustomSemaphore;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Log4j2
public class ThreadWorker implements Callable<String> {
    private final String threadName;
    private final Resource resource;
    private final Barrier barrier;
    private final CustomSemaphore semaphore;

    public ThreadWorker(String threadName, Resource resource, Barrier barrier, CustomSemaphore semaphore) {
        this.threadName = threadName;
        this.resource = resource;
        this.barrier = barrier;
        this.semaphore = semaphore;
    }

    @Override
    public String call() throws Exception {
        log.info("{}: Reaching the barrier.", threadName);
        barrier.await();

        semaphore.acquire();
        try {
            log.info("{}: Using the resource.", threadName);
            resource.setState(new ActiveState());
            resource.use(threadName);
            TimeUnit.SECONDS.sleep(1); // Simulate work
        } finally {
            semaphore.release();
            log.info("{}: Finished using the resource.", threadName);
        }
        return threadName + ": Done.";
    }
}
