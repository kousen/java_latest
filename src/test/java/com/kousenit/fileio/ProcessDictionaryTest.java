package com.kousenit.fileio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessDictionaryTest {

    @Test
    void checkMaxLengthInDictionary() {
        ProcessDictionary pd = new ProcessDictionary();
        int maxLength = pd.maxLength();
        System.out.println(maxLength);
        assertTrue(maxLength > 20);
    }
}