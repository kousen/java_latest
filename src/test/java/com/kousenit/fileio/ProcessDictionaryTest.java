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

    @Test
    void testTeeingCollectorDoesBoth() {
        // Test the teeing collector that combines count and list
        assertDoesNotThrow(() -> pd.teeingCollectorDoesBoth());
    }

    @Test
    void testPrintSortedMapOfWords() {
        // Test sorting words by length in descending order
        assertDoesNotThrow(() -> pd.printSortedMapOfWords());
    }

    @Test
    void testPrintSortedMapOfWordsUsingBufferedReader() {
        // Test the BufferedReader approach for reading the dictionary
        assertDoesNotThrow(() -> pd.printSortedMapOfWordsUsingBufferedReader());
    }

    @Test
    void testMainMethodExecutes() {
        // Test that main() runs all the demonstrations
        assertDoesNotThrow(() -> ProcessDictionary.main(new String[]{}));
    }
}