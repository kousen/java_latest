package com.kousenit.astro;

import com.kousenit.http.AstroResponse;

import java.util.Map;
import java.util.stream.Collectors;

public class AstroService {
    private final Gateway<AstroResponse> gateway;

    public AstroService(Gateway<AstroResponse> gateway) {
        this.gateway = gateway;
    }

    public Map<String, Long> getAstroData() {
        var response = gateway.getResult();

        // Pattern matching for switch (Java 21 feature)
        return switch (response) {
            case Success<AstroResponse> success -> extractMap(success.data());
            case Failure<AstroResponse> failure -> throw failure.exception();
        };

    // Without pattern matching on sealed classes:
//        if (response instanceof Success<AstroResponse> success) {
//            return extractMap(success.data());
//        } else if (response instanceof Failure<AstroResponse> failure){
//            throw failure.exception();
//        } else {
//            throw new RuntimeException("Remove when pattern matching for switch available");
//        }
    }

    private Map<String, Long> extractMap(AstroResponse data) {
        return data.people().stream()
                .collect(Collectors.groupingBy(
                        AstroResponse.Assignment::craft, Collectors.counting()));
    }
}
