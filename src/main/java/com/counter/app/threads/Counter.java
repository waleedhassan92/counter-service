package com.counter.app.threads;

import com.counter.app.data.services.ThreadTimestampDataService;
import com.counter.app.util.enums.ThreadType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@RequiredArgsConstructor
public class Counter {
    private final AtomicInteger counter = new AtomicInteger(50);
    private final ThreadTimestampDataService threadTimestampDataService;

    public synchronized void increment() {
        if (hasReachedLimit()) {
            return;
        }
        int incrementedValue = counter.incrementAndGet();

        if (incrementedValue == 100) {
            logThreadCounterData(incrementedValue, ThreadType.PRODUCER.getType());
        }
        log.info("Producer : {}, Increased Counter : {}", Thread.currentThread().getName(), incrementedValue);
    }

    public synchronized void decrement() {
        if (hasReachedLimit()) {
            return;
        }
        int decrementedValue = counter.decrementAndGet();

        if (decrementedValue == 0) {
            logThreadCounterData(decrementedValue, ThreadType.CONSUMER.getType());
        }
        log.info("Consumer : {}, Decreased Counter : {}", Thread.currentThread().getName(), decrementedValue);
    }

    public boolean hasReachedLimit() {
        return counter.get() == 100 || counter.get() == 0;
    }

    private void logThreadCounterData(int counter, String threadType) {
        threadTimestampDataService.save(threadType + " - " + Thread.currentThread().getName(), counter);
    }

    public void setCounterValue(int value) {
        counter.set(value);
    }
}