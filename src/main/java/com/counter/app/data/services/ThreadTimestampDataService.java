package com.counter.app.data.services;

import com.counter.app.models.pojo.ThreadTimestampModel;

public interface ThreadTimestampDataService {

    void save(String threadName, Integer counter);
}
