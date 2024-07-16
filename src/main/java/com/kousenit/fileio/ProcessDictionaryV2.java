package com.kousenit.fileio;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessDictionaryV2 {
    private static final Path DICTIONARY = Path.of("src/main/resources/dict/words");

    private <T> T processFile(Function<Stream<String>, T> processor) {
        try (Stream<String> words = Files.lines(DICTIONARY)) {
            return processor.apply(words);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private int maxLength() {
        return processFile(words ->
                words.mapToInt(String::length)
                        .max()
                        .orElse(0));
    }

    public void printNTenLongestWords(int n) {
        System.out.printf("\n%d Longest Words:%n", n);
        int maxForFilter = maxLength() - 10;
        processFile(words -> {
            words.filter(s -> s.length() > maxForFilter)
                    .sorted(Comparator.comparingInt(String::length).reversed())
                    .limit(n)
                    .forEach(w -> System.out.printf("%s (%d)%n", w, w.length()));
            return null;
        });
    }

    public void printWordsOfEachLength() {
        System.out.println("\nList of words of each length:");
        int maxForFilter = maxLength() - 5;
        processFile(words -> {
            words.filter(s -> s.length() > maxForFilter)
                    .collect(Collectors.groupingBy(String::length))
                    .forEach((len, wordList) -> System.out.printf("%d: %s%n", len, wordList));
            return null;
        });
    }

    public void printHowManyWordsOfEachLength() {
        System.out.println("\nNumber of words of each length:");
        int maxForFilter = maxLength() - 10;
        printWordLengthStats(s -> s.length() > maxForFilter);
    }

    public void teeingCollectorExample() {
        System.out.println("\nTeeing collector example:");
        processFile(words -> {
            record WordStats(long totalWords, double averageLength, String longestWord) {
            }

            int maxForFilter = maxLength() - 10;
            WordStats wordStats = words.filter(w -> w.length() >= maxForFilter)
                    .collect(Collectors.teeing(
                            Collectors.counting(),
                            Collectors.teeing(
                                    Collectors.averagingDouble(String::length),
                                    Collectors.maxBy(Comparator.comparingInt(String::length)),
                                    (avg, maxWord) -> new WordStats(0, avg, maxWord.orElse(""))
                            ),
                            (count, stats) -> new WordStats(count, stats.averageLength(), stats.longestWord())
                    ));

            System.out.printf("Words with length >= %d:%n", maxForFilter);
            System.out.printf("Total words: %d%n", wordStats.totalWords());
            System.out.printf("Average length: %.2f%n", wordStats.averageLength());
            System.out.printf("Longest word: %s (length: %d)%n",
                    wordStats.longestWord(), wordStats.longestWord()
                            .length());

            return null;
        });
    }

    public void printSortedMapOfWords() {
        System.out.println("\nNumber of words of each length (desc order):");
        int maxForFilter = maxLength() - 10;
        printWordLengthStats(s -> s.length() > maxForFilter);
    }

    private void printWordLengthStats(Function<String, Boolean> filter) {
        processFile(words -> {
            Map<Integer, Long> map = words.filter(filter::apply)
                    .collect(Collectors.groupingBy(String::length, Collectors.counting()));

            map.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach(e -> System.out.printf("Length %d: %d words%n", e.getKey(), e.getValue()));
            return null;
        });
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        var processDictionary = new ProcessDictionaryV2();
        processDictionary.printNTenLongestWords(10);
        processDictionary.printWordsOfEachLength();
        processDictionary.printHowManyWordsOfEachLength();
        processDictionary.teeingCollectorExample();
        processDictionary.printSortedMapOfWords();
    }
}