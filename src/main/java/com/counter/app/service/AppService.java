package com.counter.app.service;

import com.counter.app.models.pojo.Request;
import com.counter.app.models.response.Response;
import org.springframework.http.ResponseEntity;

public interface AppService {

  ResponseEntity<Response> process(Request request);

  ResponseEntity<Response> process(Integer value);
}
