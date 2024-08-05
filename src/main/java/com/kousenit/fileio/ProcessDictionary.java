package com.kousenit.fileio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@SuppressWarnings("DuplicatedCode")
public class ProcessDictionary {
    private final Path dictionary = Path.of("src/main/resources/dict/words");
    // private final Logger logger = Logger.getLogger("default");

    int maxLength() {
        try (Stream<String> words = Files.lines(dictionary)) {
            return words.mapToInt(String::length).max().orElse(0);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void printTenLongestWords() {
        System.out.println("\nTen Longest Words:");
        int maxForFilter = maxLength() - 10;
        try (Stream<String> words = Files.lines(dictionary)) {
            words.filter(s -> s.length() > maxForFilter)
                    .sorted(Comparator.comparingInt(String::length).reversed()
                            //.thenComparing(Comparator.reverseOrder()))
                    )
                    .limit(10)
                    //.forEach(w -> System.out.printf("%s (%d)%n", w, w.length()));
                    .forEach(w -> System.out.printf("%s (%d)%n", w, w.length()));
                // .forEach(w -> logger.info(() -> "the word is " + w + " and its length is " + w.length()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printWordsOfEachLength() {
        System.out.println("\nList of words of each length:");
        int maxForFilter = maxLength() - 5;
        try (Stream<String> words = Files.lines(dictionary)) {
            words.filter(s -> s.length() > maxForFilter)
                    .collect(groupingBy(String::length)) // Map<Integer,List<String>>
                    .forEach((len, wordList) -> System.out.printf("%d: %s%n", len, wordList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printHowManyWordsOfEachLength() {
        System.out.println("\nNumber of words of each length:");
        try (Stream<String> lines = Files.lines(dictionary)) {
            lines.filter(s -> s.length() > 20)
                    .collect(groupingBy(String::length, counting())) // Map<Integer,Long>
                    .forEach((len, num) -> System.out.printf("%d: %d%n", len, num));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void teeingCollectorDoesBoth() {
        System.out.println("\nTeeing collector:");
        try (Stream<String> lines = Files.lines(dictionary)) {
            var map = lines.filter(s -> s.length() > 20)
                    .collect(Collectors.teeing(
                                groupingBy(String::length, counting()), // Map<Integer,Long>
                                groupingBy(String::length),             // Map<Integer,List<String>>
                                (map1, map2) -> map1.entrySet().stream().collect(
                                        Collectors.toMap(
                                                Map.Entry::getKey,  // length of word
                                                e -> Map.of("count", e.getValue(),
                                                        "words", map2.get(e.getKey())))))
                            );
            map.forEach((k, v) -> System.out.printf("%d: %s%n", k, v));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printSortedMapOfWords() {
        System.out.println("\nNumber of words of each length (desc order):");
        try (Stream<String> lines = Files.lines(dictionary)) {
            Map<Integer, Long> map = lines.filter(s -> s.length() > 20)
                    .collect(groupingBy(String::length, counting()));

            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach(e -> System.out.printf("Length %d: %d words%n", e.getKey(), e.getValue()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printSortedMapOfWordsUsingBufferedReader() {
        System.out.println("\nNumber of words of each length (desc order):");
        try (Stream<String> lines = new BufferedReader(new FileReader("src/main/resources/dict/words")).lines()) {
            Map<Integer, Long> map = lines.filter(s -> s.length() > 20)
                    .collect(groupingBy(String::length, counting()));

            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach(e -> System.out.printf("Length %d: %d words%n", e.getKey(), e.getValue()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        ProcessDictionary processDictionary = new ProcessDictionary();
        processDictionary.printTenLongestWords();
        processDictionary.printWordsOfEachLength();
        processDictionary.printHowManyWordsOfEachLength();
        processDictionary.teeingCollectorDoesBoth();
        processDictionary.printSortedMapOfWords();
        processDictionary.printSortedMapOfWordsUsingBufferedReader();
    }
}
