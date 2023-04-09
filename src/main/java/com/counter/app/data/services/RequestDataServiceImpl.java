package com.counter.app.data.services;

import com.counter.app.data.entities.RequestLog;
import com.counter.app.data.repositories.RequestLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        RequestLog log = new RequestLog();
        log.setRequest(payload);
        log.setTimestamp(new Date());
        return log;
    }
}
