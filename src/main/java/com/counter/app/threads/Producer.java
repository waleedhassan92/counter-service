package com.counter.app.threads;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class Producer extends Thread {
    private final Counter counter;

    @Override
    public void run() {
        log.info("Producer Created : {}", Thread.currentThread().getName());
        while (true) {
            if (counter.hasReachedUpperLimit()) {
                return;
            }
            counter.increment();
            suspendThread();
        }
    }

    private void suspendThread() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.info("Producer : {} has been interrupted", Thread.currentThread().getName());
            throw new RuntimeException(e);
        }
    }
}