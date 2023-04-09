package com.counter.app.exceptions;

import com.counter.app.models.response.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RequestValidationException.class})
    public ResponseEntity<Object> handleException(RequestValidationException ex, WebRequest request) {
        String errorMessageDetail = ex.getLocalizedMessage();
        if (ObjectUtils.isEmpty(errorMessageDetail)) {
            errorMessageDetail = ex.toString();
        }
        ErrorResponse response = new ErrorResponse();
        response.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.setErrors(errorMessageDetail);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {JsonProcessingException.class})
    public ResponseEntity<Object> handleException(JsonProcessingException ex, WebRequest request) {
        String errorMessageDetail = ex.getLocalizedMessage();
        if (ObjectUtils.isEmpty(errorMessageDetail)) {
            errorMessageDetail = ex.toString();
        }
        ErrorResponse response = createErrorResponse(errorMessageDetail);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse createErrorResponse(String errorMessageDetail) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        response.setErrors(errorMessageDetail);
        return response;
    }
}
