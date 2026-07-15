package com.kousenit.openrouter;

import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.kousenit.openrouter.OpenRouterRecords.*;

// Combines records, the HTTP Client API, and virtual threads:
// ask several AI models the same question concurrently
public class OpenRouterClient {
    private static final String CHAT_URL = "https://openrouter.ai/api/v1/chat/completions";
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    private final ObjectMapper mapper = new ObjectMapper();

    public String chat(String model, String prompt) {
        String key = System.getenv("OPENROUTER_API_KEY");
        if (key == null) {
            throw new IllegalStateException("OPENROUTER_API_KEY environment variable not set");
        }
        String requestBody = mapper.writeValueAsString(
                new ChatRequest(model, List.of(new Message("user", prompt))));
        var request = HttpRequest.newBuilder()
                .uri(URI.create(CHAT_URL))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer %s".formatted(key))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            HttpResponse<String> response =
                    HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException(
                        "HTTP %d: %s".formatted(response.statusCode(), response.body()));
            }
            return mapper.readValue(response.body(), ChatResponse.class).text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    // One virtual thread per model; total time is roughly the slowest
    // single response rather than the sum of them all
    public List<ModelAnswer> compareModels(List<String> models, String prompt) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<ModelAnswer>> futures = models.stream()
                    .map(model -> executor.submit(
                            () -> new ModelAnswer(model, chat(model, prompt))))
                    .toList();
            return futures.stream()
                    .map(this::getAnswer)
                    .toList();
        }
    }

    private ModelAnswer getAnswer(Future<ModelAnswer> future) {
        try {
            return future.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    static void main() {
        var client = new OpenRouterClient();
        var models = List.of(
                "tencent/hy3:free",
                "google/gemini-3.5-flash",
                "deepseek/deepseek-v4-flash",
                "z-ai/glm-5.2");
        client.compareModels(models, "What is the capital of France? Answer in one sentence.")
                .forEach(answer ->
                        System.out.printf("%s:%n  %s%n", answer.model(), answer.answer()));
    }
}
