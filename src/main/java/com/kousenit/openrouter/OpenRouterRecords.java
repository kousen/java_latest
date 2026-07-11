package com.kousenit.openrouter;

import java.util.List;

// OpenRouter (https://openrouter.ai) exposes many AI models behind one
// OpenAI-compatible REST API, so we don't have to pick a winner
public class OpenRouterRecords {

    public record Message(String role, String content) {}

    public record ChatRequest(String model, List<Message> messages) {}

    public record Choice(Message message) {}

    public record ChatResponse(String id, String model, List<Choice> choices) {
        public String text() {
            return choices.getFirst().message().content();
        }
    }

    public record ModelAnswer(String model, String answer) {}
}
