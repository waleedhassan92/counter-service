package com.counter.app.data.repositories;

import com.counter.app.data.entities.ThreadTimestamp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadTimestampRepository extends CrudRepository<ThreadTimestamp, Long> {
}
