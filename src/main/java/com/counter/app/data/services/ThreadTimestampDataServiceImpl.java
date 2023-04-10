package com.counter.app.data.services;

import com.counter.app.data.entities.ThreadTimestamp;
import com.counter.app.data.repositories.ThreadTimestampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class ThreadTimestampDataServiceImpl implements ThreadTimestampDataService {

    private final ThreadTimestampRepository threadTimestampRepository;

    @Override
    public void save(String threadName, Integer counter) {
        threadTimestampRepository.save(createEntity(threadName, counter));
    }

    private ThreadTimestamp createEntity(String threadName, Integer counter) {
        return ThreadTimestamp.builder()
                .threadName(threadName)
                .counter(counter)
                .timestamp(OffsetDateTime.now())
                .build();
    }
}
