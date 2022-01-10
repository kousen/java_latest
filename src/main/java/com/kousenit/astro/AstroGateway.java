package com.kousenit.astro;

import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@SuppressWarnings("HttpUrlsUsage")
public class AstroGateway implements Gateway<AstroResponse> {
    private static final String DEFAULT_URL = "http://api.open-notify.org/astros.json";
    private final String url;

    public AstroGateway() {
        this(DEFAULT_URL);
    }

    public AstroGateway(String url) {
        this.url = url;
    }

    private final HttpClient client = HttpClient.newHttpClient();

    private final JsonMapper jsonMapper = new JsonMapper();

    @Override
    public Result<AstroResponse> getResponse() {
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                    .header("Accept", "application/json")
                    .GET() // default (could leave that out)
                    .build();
            HttpResponse<String> httpResponse =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Success<>(
                    jsonMapper.readValue(httpResponse.body(), AstroResponse.class));
        } catch (IOException | InterruptedException e) {
            return new Failure<>(new RuntimeException(e));
        }
    }
}

record Assignment(String name, String craft) {
}

record AstroResponse(int number,
                     String message,
                     List<Assignment> people) {
}

