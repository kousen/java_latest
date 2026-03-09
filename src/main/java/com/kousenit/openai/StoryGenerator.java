package com.kousenit.openai;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.json.JsonMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.kousenit.openai.OpenAiRecords.*;

public class StoryGenerator {
    // HttpClient to transmit requests to the OpenAI API
    private final HttpClient client = HttpClient.newHttpClient();

    // Record to hold input data
    public record OpenAiRequest(String input, String model) {

        public OpenAiRequest {
            if (input == null || input.isBlank()) {
                throw new IllegalArgumentException("Input cannot be null or blank");
            }
            if (model == null || model.isBlank()) {
                throw new IllegalArgumentException("Model cannot be null or blank");
            }
        }
    }

    // JSON parser to parse and generate JSON responses
    private final ObjectMapper objectMapper = JsonMapper.builder()
            .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .build();

    // Business logic to generate stories
    public String generateStory(String topic) {
        var openAiRequest = new OpenAiRequest("""
                Write a short story about %s.
                """.formatted(topic),
                "gpt-5.4");
        var jsonRequest = objectMapper.writeValueAsString(openAiRequest);

        var request = HttpRequest.newBuilder(URI.create("https://api.openai.com/v1/responses"))
                .header("Authorization", "Bearer %s".formatted(System.getenv("OPENAI_API_KEY")))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (httpResponse.statusCode() != 200) {
                throw new RuntimeException("OpenAI API returned status code: " + httpResponse.statusCode());
            }
            var apiResponse = objectMapper.readValue(httpResponse.body(), ApiResponse.class);
            return apiResponse.output().getFirst().content().getFirst().text();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate story", e);
        }
    }

    static void main() {
        var storyGenerator = new StoryGenerator();
        System.out.println(storyGenerator.generateStory("AI coding in Java"));
    }
}
