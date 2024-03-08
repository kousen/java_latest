package com.kousenit.fileio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessDictionaryTest {
    private final ProcessDictionary pd = new ProcessDictionary();

    @Test
    void checkMaxLengthInDictionary() {
        int maxLength = pd.maxLength();
        System.out.println(maxLength);
        assertTrue(maxLength > 20);
    }

    @Test
    void printTenLongestWords() {
        pd.printTenLongestWords();
    }

    @Test
    void printWordsOfEachLength() {
        pd.printWordsOfEachLength();
    }

    @Test
    void printHowManyWordsOfEachLength() {
        pd.printHowManyWordsOfEachLength();
    }
}