package com.example.threading;

import com.example.threading.utils.Barrier;
import com.example.threading.utils.CustomSemaphore;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Log4j2
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int totalThreads = 5;
        int maxConcurrentThreads = 2;

        Resource resource = new Resource();
        Barrier barrier = new Barrier(totalThreads);
        CustomSemaphore semaphore = new CustomSemaphore(maxConcurrentThreads);

        ExecutorService executor = Executors.newFixedThreadPool(totalThreads);
        List<Callable<String>> workers = new ArrayList<>();

        for (int i = 0; i < totalThreads; i++) {
            workers.add(new ThreadWorker("Thread-" + (i + 1), resource, barrier, semaphore));
        }

        List<Future<String>> results = executor.invokeAll(workers);

        for (Future<String> result : results) {
            log.info(result.get());
        }

        executor.shutdown();
    }
}
