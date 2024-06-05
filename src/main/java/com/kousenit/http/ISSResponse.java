package com.kousenit.http;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public record ISSResponse(
        String message,
        long timestamp,
        Position issPosition) {

    @Override
    public String toString() {
        return "ISSResponse{message='%s', timestamp=%s, issPosition=%s}"
                    .formatted(message,
                            LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp),
                                    ZoneId.systemDefault()),
                            issPosition);
    }

    public record Position(
            double latitude,
            double longitude) {
    }
}

