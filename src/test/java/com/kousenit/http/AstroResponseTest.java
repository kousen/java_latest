package com.kousenit.http;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AstroResponseTest {

    private final Gson gson = new Gson();

    @Test
    void testSuccess() {
        String json = """
                {
                  "message": "success",
                  "number": 3,
                  "people": [
                    {
                      "name": "Neal Armstrong",
                      "craft": "Eagle"
                    },
                    {
                      "name": "Buzz Aldrin",
                      "craft": "Eagle"
                    },
                    {
                      "name": "Michael Collins",
                      "craft": "Columbia"
                    }
                  ]
                }
                """;
        AstroResponse response = gson.fromJson(json, AstroResponse.class);
        assertAll(
                () -> assertEquals("success", response.message()),
                () -> assertEquals(3, response.number()),
                () -> assertEquals(response.number(), response.people().size())
        );
    }

    @Test
    void testFailure() {
        String json = """
                {
                  "message": "failure",
                  "number": 3,
                  "people": [
                    {
                      "name": "Jim Lovell",
                      "craft": "Apollo 13"
                    },
                    {
                      "name": "Jack Swigert",
                      "craft": "Apollo 13"
                    },
                    {
                      "name": "Fred Haise",
                      "craft": "Apollo 13"
                    }
                  ]
                }
                """;

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> gson.fromJson(json, AstroResponse.class))
                .withMessageContaining("Failed to invoke constructor")
                .withCauseExactlyInstanceOf(IllegalArgumentException.class)
                .extracting(Throwable::getCause)
                .extracting(Throwable::getMessage)
                .isEqualTo("Houston, we have a problem");
    }

}