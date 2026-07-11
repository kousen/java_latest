package com.kousenit.openrouter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class OpenRouterClientTest {
    private final OpenRouterClient client = new OpenRouterClient();

    @BeforeEach
    void setUp() {
        assumeTrue(System.getenv("OPENROUTER_API_KEY") != null,
                "OPENROUTER_API_KEY not set");
    }

    @Test
    void chatWithSingleModel() {
        String answer = client.chat("google/gemini-3.5-flash",
                "Why do Java developers wear glasses? Answer in one sentence.");
        System.out.println(answer);
        assertThat(answer).isNotBlank();
    }

    @Test
    void compareModelsOnVirtualThreads() {
        var models = List.of(
                "google/gemini-3.5-flash",
                "deepseek/deepseek-v4-pro",
                "z-ai/glm-5.2");

        var answers = client.compareModels(models,
                "What is the capital of France? Answer in one sentence.");

        answers.forEach(answer ->
                System.out.printf("%s:%n  %s%n", answer.model(), answer.answer()));
        assertThat(answers)
                .hasSize(models.size())
                .allSatisfy(answer -> assertThat(answer.answer()).isNotBlank());
    }
}
