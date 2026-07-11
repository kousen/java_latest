package com.kousenit.openai;

import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static com.kousenit.openai.ChatRecords.*;

public class Chat {

    static void main() {
        String prompt = """
                According to Douglas Adams,
                What is the answer to the
                Ultimate Question of Life,
                the Universe, and Everything?
                """;
        String model = "gpt-5.6-luna";

        var mapper = new ObjectMapper();
        var inputJson = mapper.writeValueAsString(new ChatInput(model, prompt));

        String key = System.getenv("OPENAI_API_KEY");
        if (key == null) {
            throw new IllegalStateException("OPENAI_API_KEY environment variable not set");
        }
        try (var client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .version(HttpClient.Version.HTTP_2)
                .build()) {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.openai.com/v1/responses"))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer %s".formatted(key))
                    .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.printf("Response status code: %d%n".formatted(response.statusCode()));
            var chatResponse = mapper.readValue(response.body(), ChatResponse.class);
            System.out.println(chatResponse);
            System.out.println(chatResponse.output().get(1).content().getFirst().text());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
