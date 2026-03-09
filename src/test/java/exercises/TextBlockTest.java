package exercises;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.json.JsonMapper;

import static com.kousenit.openai.OpenAiRecords.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextBlockTest {
    private final ObjectMapper objectMapper = JsonMapper.builder()
            .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .build();

    @Test
    void basicTextBlock() {
        String json = """
                {
                  "id": "resp_67ccd2bed1ec8190b14f964abc0542670bb6a6b452d3795b",
                  "object": "response",
                  "created_at": 1741476542,
                  "status": "completed",
                  "completed_at": 1741476543,
                  "error": null,
                  "incomplete_details": null,
                  "instructions": null,
                  "max_output_tokens": null,
                  "model": "gpt-5.4",
                  "output": [
                    {
                      "type": "message",
                      "id": "msg_67ccd2bf17f0819081ff3bb2cf6508e60bb6a6b452d3795b",
                      "status": "completed",
                      "role": "assistant",
                      "content": [
                        {
                          "type": "output_text",
                          "text": "In a peaceful grove beneath a silver moon, a unicorn named Lumina discovered a hidden pool that reflected the stars. As she dipped her horn into the water, the pool began to shimmer, revealing a pathway to a magical realm of endless night skies. Filled with wonder, Lumina whispered a wish for all who dream to find their own hidden magic, and as she glanced back, her hoofprints sparkled like stardust.",
                          "annotations": []
                        }
                      ]
                    }
                  ],
                  "parallel_tool_calls": true,
                  "previous_response_id": null,
                  "reasoning": {
                    "effort": null,
                    "summary": null
                  },
                  "store": true,
                  "temperature": 1.0,
                  "text": {
                    "format": {
                      "type": "text"
                    }
                  },
                  "tool_choice": "auto",
                  "tools": [],
                  "top_p": 1.0,
                  "truncation": "disabled",
                  "usage": {
                    "input_tokens": 36,
                    "input_tokens_details": {
                      "cached_tokens": 0
                    },
                    "output_tokens": 87,
                    "output_tokens_details": {
                      "reasoning_tokens": 0
                    },
                    "total_tokens": 123
                  },
                  "user": null,
                  "metadata": {}
                }
                """;
        assertTrue(json.contains("unicorn"));

        ApiResponse response = objectMapper.readValue(json, ApiResponse.class);
        assertEquals("gpt-5.4", response.model());
        System.out.println(response.output().getFirst().content().getFirst().text());
    }

    @Test
    void textBlockWithFormatting() {
        String name = "Java";
        int version = 21;

        record Version(String name, int version) {
        }

        String message = """
                {
                    "name": "%s",
                    "version": %d
                }
                """.formatted(name, version);

        assertTrue(message.contains("Java"));
        assertTrue(message.contains("21"));

        var versionRecord = objectMapper.readValue(message, Version.class);
        assertEquals(name, versionRecord.name());
        assertEquals(version, versionRecord.version());
    }

    @Test
    void sqlQuery() {
        String query = """
                SELECT u.id, u.name, COUNT(o.id) as order_count
                FROM users u
                LEFT JOIN orders o ON u.id = o.user_id
                WHERE u.active = true
                GROUP BY u.id, u.name
                HAVING COUNT(o.id) > ?
                """;

        assertTrue(query.contains("SELECT"));
        assertTrue(query.contains("GROUP BY"));
    }

    @Test
    void htmlTemplate() {
         String html = """
             <!DOCTYPE html>
             <html>
                 <head>
                     <title>%s</title>
                 </head>
                 <body>
                     <h1>%s</h1>
                     <p>%s</p>
                 </body>
             </html>
             """.formatted("My Page", "Welcome", "Hello, World!");

         assertTrue(html.contains("<title>My Page</title>"));
    }
}
