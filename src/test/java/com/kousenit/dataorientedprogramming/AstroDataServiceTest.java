package com.kousenit.dataorientedprogramming;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.kousenit.dataorientedprogramming.AstroDataService.Result;
import static org.assertj.core.api.Assertions.assertThat;

class AstroDataServiceTest {

    private final AstroDataService processor = new AstroDataService();

    @Test
    void testSuccessfulDataFetch() {
        Result result = processor.fetchAndProcessData();

        switch (result) {
            case Result.Failure failure -> assertThat(failure.error()).contains("HTTP error");
            case Result.Success success -> {
                Map<String, List<String>> astronautsByCraft = success.astronautsByCraft();
                assertThat(astronautsByCraft)
                        .hasSize(2)
                        .containsKeys("ISS", "Tiangong");
            }
        }
    }
}