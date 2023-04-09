package com.counter.app.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ThreadType {
    PRODUCER("Producer"),
    CONSUMER("Consumer");

    private final String type;
}
