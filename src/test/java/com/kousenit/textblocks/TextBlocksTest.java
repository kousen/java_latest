package com.kousenit.textblocks;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import com.kousenit.http.AstroResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TextBlocksTest {
    private final TextBlocks textBlocks = new TextBlocks();

    @Test
    void helloTextBlock() {
        System.out.println(textBlocks.hello());
    }

    @Test
    void astroData() {
        System.out.println(textBlocks.json().stripIndent());
    }

    @Test
    void colorsFromJEP_eachLineHasSixCharactersExactly() {
        String colors = """
                red  \s
                green\s
                blue \s
                """;
        System.out.println(colors);
    }

    @Test
    void getAstroData() throws Exception {
        String data = textBlocks.getAstroData();
        System.out.println(data);

        // Jackson 2 deserializes to record types
        JsonMapper jsonMapper = new JsonMapper();
        AstroResponse astroResponse = jsonMapper.readValue(data, AstroResponse.class);
        System.out.println(astroResponse);
        System.out.println("There are " + astroResponse.number() + " people in space");
    }
}