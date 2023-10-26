package com.kousenit.textblocks;

import org.junit.jupiter.api.Test;

public class TextBlocksTest {
    private final TextBlocks textBlocks = new TextBlocks();

    @Test
    void helloTextBlock() {
        System.out.println(textBlocks.hello());
        System.out.println(textBlocks.hello("Dolly"));

    }

    @Test
    void astroData() {
        System.out.println(textBlocks.json().stripIndent());
    }

    @Test
    void colorsFromJEP_eachLineHasFiveCharactersExactly() {
        String colors = """
                red  \s
                green\s
                blue \s
                """;
        System.out.println(colors);
        colors.lines().forEach(line -> System.out.println(line.length()));
    }
}