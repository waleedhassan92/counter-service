package com.counter.app.models.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    private Integer producers;
    private Integer consumers;

    @Override
    public String toString() {
        return "{producers: " + producers + ", consumers: " + consumers + "}";
    }
}
