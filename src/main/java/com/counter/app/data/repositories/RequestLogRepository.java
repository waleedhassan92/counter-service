package com.counter.app.data.repositories;

import com.counter.app.data.entities.RequestLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogRepository extends CrudRepository<RequestLog, Long> {
}
