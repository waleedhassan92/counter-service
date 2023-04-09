package com.counter.app.controllers;

import com.counter.app.models.pojo.Request;
import com.counter.app.models.response.Response;
import com.counter.app.service.AppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;

    @PostMapping(value = "/create")
    public ResponseEntity<Response> create(@RequestBody Request request) {
        log.info("Create Request Received : {}", request.toString());
        return appService.process(request);
    }

    @PostMapping(value = "/reset")
    public ResponseEntity<Response> reset(@RequestParam("counter") Integer counter) {
        log.info("Reset Request Received. Counter : {}", counter);
        return appService.process(counter);
    }

}
