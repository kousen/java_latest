package exercises;

import java.util.List;
import java.util.Map;

public class OpenAiRecords {

    record ApiResponse(
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

    record OutputMessage(
            String type,
            String id,
            String status,
            String role,
            List<Content> content
    ) {}

    record Content(
            String type,
            String text,
            List<Object> annotations
    ) {}

    record Reasoning(
            String effort,
            String summary
    ) {}

    record TextFormat(
            Format format
    ) {}

    record Format(
            String type
    ) {}

    record Usage(
            int inputTokens,
            TokenDetails inputTokensDetails,
            int outputTokens,
            OutputTokenDetails outputTokensDetails,
            int totalTokens
    ) {}

    record TokenDetails(
            int cachedTokens
    ) {}

    record OutputTokenDetails(
            int reasoningTokens
    ) {}
}
