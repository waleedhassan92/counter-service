package com.counter.app.models.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ThreadTimestampModel {
    private String threadName;
    private String counterValue;
}
