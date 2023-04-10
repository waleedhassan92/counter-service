package com.counter.app.data.services;

import com.counter.app.data.entities.RequestLog;
import com.counter.app.data.repositories.RequestLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestDataServiceImpl implements RequestDataService {

    private final RequestLogRepository requestLogRepository;

    @Override
    public void save(String payload) {
        requestLogRepository.save(createEntity(payload));
    }

    private RequestLog createEntity(String payload) {
        return RequestLog.builder()
                .request(payload)
                .timestamp(OffsetDateTime.now())
                .build();
    }
}
