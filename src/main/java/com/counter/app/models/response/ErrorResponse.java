package com.counter.app.models.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse extends Response {
    private String errors;
}