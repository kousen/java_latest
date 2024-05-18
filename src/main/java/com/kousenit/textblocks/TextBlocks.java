package com.kousenit.textblocks;

public class TextBlocks {
    public String hello() {
        return """
                    This is a
                        multiline string
                with newlines inside
                """;
    }

    public String hello(String name) {
        return """
                            Hello, %s!
                            This is a
                                multiline string
                        with newlines inside \
                        """.formatted(name);
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
                """.stripIndent();
    }

    public String sql() {
        return """
                SELECT * FROM people
                WHERE first_name = 'Joshua'
                AND last_name = 'Bloch'
                """;
    }
}
