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
    public void initializeProducers(Counter counter) {
        new Producer(counter).start();
    }

    // Creating Consumer threads asynchronously
    @Async
    public void initializeConsumer(Counter counter) {
        new Consumer(counter).start();
    }
}
