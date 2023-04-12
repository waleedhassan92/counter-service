package com.counter.app.service.impl;

import com.counter.app.data.services.RequestDataService;
import com.counter.app.exceptions.RequestValidationException;
import com.counter.app.models.pojo.Request;
import com.counter.app.models.response.Response;
import com.counter.app.service.AppService;
import com.counter.app.service.ThreadInitializerService;
import com.counter.app.threads.Consumer;
import com.counter.app.threads.Counter;
import com.counter.app.threads.Producer;
import com.counter.app.util.constants.Errors;
import com.counter.app.util.constants.ResponseMessage;
import com.counter.app.util.factories.ResponseBuilderFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public final class AppServiceImpl implements AppService {

    private final RequestDataService requestDataService;
    private final Counter counter;
    private final ObjectMapper mapper;
    private final ThreadInitializerService threadInitializerService;

    @Override
    public ResponseEntity<Response> process(Request request) {

        logRequest(request);
        validateRequest(request);

        if (counter.hasReachedLimit()) {
            return ResponseBuilderFactory.buildCreateApiSuccessResponse(ResponseMessage.LIMIT_REACHED_MESSAGE);
        }

        createThreads(request.getProducers(), request.getConsumers());

        return ResponseBuilderFactory.buildCreateApiSuccessResponse(
                ResponseMessage.THREAD_CREATION_SUCCESS_MESSAGE);
    }

    @Override
    public ResponseEntity<Response> process(Integer value) {
        counter.setCounterValue(value);
        return ResponseBuilderFactory.buildResetApiSuccessResponse(value);
    }

    // Logging incoming requests in DB
    private void logRequest(Request request) {
        try {
            String jsonRequest = mapper.writeValueAsString(request);
            requestDataService.save(jsonRequest);
        } catch (JsonProcessingException e) {
            log.error(Errors.INTERNAL_SERVER_ERROR);
            throw new RuntimeException(Errors.INTERNAL_SERVER_ERROR);
        }
    }

    // Validating request for non negative values
    private void validateRequest(Request request) {
        if (request.getProducers() < 0
                || request.getConsumers() < 0) {
            throw new RequestValidationException(Errors.NEGATIVE_NUMBER_NOT_ALLOWED);
        }
    }

    private void createThreads(Integer producers, Integer consumers) {
        for (int i = 0; i < producers; i++) {
            threadInitializerService.initializeProducers(counter);
        }

        for (int i = 0; i < consumers; i++) {
            threadInitializerService.initializeConsumer(counter);
        }
    }
}
