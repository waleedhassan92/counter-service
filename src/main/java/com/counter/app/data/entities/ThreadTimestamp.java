package com.counter.app.data.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "thread_timestamp")
public class ThreadTimestamp implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @EqualsAndHashCode.Include
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "thread_name")
    private String threadName;

    @Column(name = "counter")
    private Integer counter;

    @Column(name = "timestamp")
    private OffsetDateTime timestamp;
}
