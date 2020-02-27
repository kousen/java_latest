package com.kousenit.textblocks;

import com.kousenit.textblocks.TextBlocks;
import org.junit.jupiter.api.Test;

public class TextBlocksTest {
    private final TextBlocks textBlocks = new TextBlocks();

    @Test
    void helloTextBlock() {
        String hello = textBlocks.hello();
    }

    @Test
    void astroData() {
        System.out.println(textBlocks.json().stripIndent());
    }

    @Test
    void getAstroData() {
        String data = textBlocks.getAstroData();
        System.out.println(data);
    }
}