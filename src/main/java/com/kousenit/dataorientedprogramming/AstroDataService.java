package com.kousenit.dataorientedprogramming;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AstroDataService {
    private static final String API_URL = "http://api.open-notify.org/astros.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Record to represent an astronaut
    public record Astronaut(String name, String craft) { }

    // Record to represent the API response
    public record AstroResponse(List<Astronaut> people, int number, String message) { }

    // Sealed interface for processing results
    public sealed interface Result {
        record Success(Map<String, List<String>> astronautsByCraft) implements Result { }
        record Failure(String error) implements Result { }
    }

    public Result fetchAndProcessData() {
        try (var client = HttpClient.newHttpClient()) {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Accept", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return new Result.Failure("HTTP error: " + response.statusCode());
            }

            AstroResponse astroResponse = objectMapper.readValue(response.body(), AstroResponse.class);

            Map<String, List<String>> astronautsByCraft = groupAstronautsByCraft(astroResponse);

            return new Result.Success(astronautsByCraft);
        } catch (Exception e) {
            return new Result.Failure("Error processing data: " + e.getMessage());
        }
    }

    private Map<String, List<String>> groupAstronautsByCraft(AstroResponse astroResponse) {
        return astroResponse.people().stream()
                .collect(Collectors.groupingBy(Astronaut::craft,
                        Collectors.mapping(Astronaut::name, Collectors.toList())));
    }

    public void printResults(Map<String, List<String>> astronautsByCraft) {
        System.out.println("Astronauts currently in space:");
        astronautsByCraft.forEach((craft, astronauts) -> {
            System.out.println(craft + ":");
            astronauts.forEach(name -> System.out.println("  - " + name));
        });
    }

    public static void main(String[] args) {
        var processor = new AstroDataService();
        Result result = processor.fetchAndProcessData();

        switch (result) {
            case Result.Success success -> processor.printResults(success.astronautsByCraft());
            case Result.Failure failure -> System.out.println("Error: " + failure.error());
        }
    }
}
