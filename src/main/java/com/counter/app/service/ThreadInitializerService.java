package com.counter.app.service;

import com.counter.app.threads.Consumer;
import com.counter.app.threads.Counter;
import com.counter.app.threads.Producer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadInitializerService {

    // Creating Producer threads asynchronously
    @Async
    public void initializeProducers(Integer producers, Counter counter) {
        for (int i = 0; i < producers; i++) {
            new Producer(counter).start();
        }
    }

    // Creating Consumer threads asynchronously
    @Async
    public void initializeConsumer(Integer consumers, Counter counter) {
        for (int i = 0; i < consumers; i++) {
            new Consumer(counter).start();
        }
    }
}
