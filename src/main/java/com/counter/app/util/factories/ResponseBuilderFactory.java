package com.counter.app.util.factories;

import com.counter.app.models.response.Response;
import com.counter.app.util.constants.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilderFactory {


    private ResponseBuilderFactory() {
    }

    public static ResponseEntity<Response> buildCreateApiSuccessResponse(String message) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Response.builder()
                        .status(HttpStatus.CREATED.getReasonPhrase())
                        .message(message)
                        .build());
    }

    public static ResponseEntity<Response> buildResetApiSuccessResponse(Integer counter) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Response.builder()
                        .status(HttpStatus.OK.getReasonPhrase())
                        .message(String.format(ResponseMessage.COUNTER_RESET_MESSAGE, counter))
                        .build());
    }
}