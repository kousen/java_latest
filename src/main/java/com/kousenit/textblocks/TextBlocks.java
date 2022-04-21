package com.kousenit.textblocks;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TextBlocks {
    public String hello() {
        return """
                    This is a
                        multiline string
                with newlines inside
                """;
    }

    public String json() {
        return """
                        {
                            "people": [{"craft": "ISS", "name": "Andrew Morgan"},
                                       {"craft": "ISS", "name": "Oleg Skripochka"},
                                       {"craft": "ISS", "name": "Jessica Meir"}],
                            "message": "success",
                            "number": 3
                        }
                """;
    }

    public String getAstroData() {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://api.open-notify.org/astros.json")).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}
