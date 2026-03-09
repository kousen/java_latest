package com.kousenit.openai;

import java.util.List;
import java.util.Map;

public class OpenAiRecords {

    public record ApiResponse(
            String id,
            String object,
            long createdAt,
            String status,
            Long completedAt,
            String error,
            String incompleteDetails,
            String instructions,
            Integer maxOutputTokens,
            String model,
            List<OutputMessage> output,
            boolean parallelToolCalls,
            String previousResponseId,
            Reasoning reasoning,
            boolean store,
            double temperature,
            TextFormat text,
            String toolChoice,
            List<Object> tools,
            double topP,
            String truncation,
            Usage usage,
            String user,
            Map<String, Object> metadata
    ) {}

    public record OutputMessage(
            String type,
            String id,
            String status,
            String role,
            List<Content> content
    ) {}

    public record Content(
            String type,
            String text,
            List<Object> annotations
    ) {}

    public record Reasoning(
            String effort,
            String summary
    ) {}

    public record TextFormat(
            Format format
    ) {}

    public record Format(
            String type
    ) {}

    public record Usage(
            int inputTokens,
            TokenDetails inputTokensDetails,
            int outputTokens,
            OutputTokenDetails outputTokensDetails,
            int totalTokens
    ) {}

    public record TokenDetails(
            int cachedTokens
    ) {}

    public record OutputTokenDetails(
            int reasoningTokens
    ) {}
}
