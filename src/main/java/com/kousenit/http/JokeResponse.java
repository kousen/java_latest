package com.kousenit.http;

import java.util.List;

// Response from https://api.chucknorris.io/jokes/random
public record JokeResponse(String id, String value, List<String> categories) {
}
