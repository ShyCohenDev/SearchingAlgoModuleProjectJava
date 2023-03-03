package com.hit.algorithm;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IAlgoSearchTest {

    @Test
    void perfectSearchTest_impl01() {
        System.out.println("running perfectSearchTest_impl01...");

        Map<String, String> items = new TreeMap<>();
        items.put("1", "dog");
        items.put("2", "cat");
        items.put("3", "red dog");
        items.put("4", "doh");
        items.put("5", "dgo");
        items.put("6", "dogs");

        IAlgoSearch iAlgoSearch = new Levenshtein_KMP_AlgoSearchImpl(items);
        Set<String> results = iAlgoSearch.perfectSearch("dog");

        assertEquals(results, new TreeSet<>(List.of("1")));
    }
    @Test
    void containsSearchTest_impl01() {
        System.out.println("running containsSearchTest_impl01...");

        Map<String, String> items = new TreeMap<>();
        items.put("1", "dog");
        items.put("2", "cat");
        items.put("3", "red dog");
        items.put("4", "doh");
        items.put("5", "dgo");
        items.put("6", "dogs");

        IAlgoSearch iAlgoSearch = new Levenshtein_KMP_AlgoSearchImpl(items);
        Set<String> results = iAlgoSearch.containsSearch("dog");

        assertEquals(results, new TreeSet<>(Arrays.asList("1", "3", "6")));
    }
    @Test
    void fuzzySearchTest_impl01() {
        System.out.println("running fuzzySearchTest_impl01...");

        Map<String, String> items = new TreeMap<>();
        items.put("1", "dog");
        items.put("2", "cat");
        items.put("3", "red dog");
        items.put("4", "doh");
        items.put("5", "dgo");
        items.put("6", "dogs");

        IAlgoSearch iAlgoSearch = new Levenshtein_KMP_AlgoSearchImpl(items);
        Set<String> results = iAlgoSearch.fuzzySearch("dog", 2);

        assertEquals(results, new TreeSet<>(Arrays.asList("1", "4", "5", "6")));
    }

    @Test
    void perfectSearchTest_impl02() {
        System.out.println("running perfectSearchTest_impl02...");

        Map<String, String> items = new TreeMap<>();
        items.put("1", "dog");
        items.put("2", "cat");
        items.put("3", "red dog");
        items.put("4", "doh");
        items.put("5", "dgo");
        items.put("6", "dogs");

        IAlgoSearch iAlgoSearch = new DamerauLevenshtien_contains_AlgoSearchImpl(items);
        Set<String> results = iAlgoSearch.perfectSearch("dog");

        assertEquals(results, new TreeSet<>(List.of("1")));
    }
    @Test
    void containsSearchTest_impl02() {
        System.out.println("running containsSearchTest_impl02...");

        Map<String, String> items = new TreeMap<>();
        items.put("1", "dog");
        items.put("2", "cat");
        items.put("3", "red dog");
        items.put("4", "doh");
        items.put("5", "dgo");
        items.put("6", "dogs");

        IAlgoSearch iAlgoSearch = new DamerauLevenshtien_contains_AlgoSearchImpl(items);
        Set<String> results = iAlgoSearch.containsSearch("dog");

        assertEquals(results, new TreeSet<>(Arrays.asList("1", "3", "6")));
    }
    @Test
    void fuzzySearchTest_impl02() {
        System.out.println("running fuzzySearchTest_impl02...");

        Map<String, String> items = new TreeMap<>();
        items.put("1", "dog");
        items.put("2", "cat");
        items.put("3", "red dog");
        items.put("4", "doh");
        items.put("5", "dgo");
        items.put("6", "dogs");

        IAlgoSearch iAlgoSearch = new DamerauLevenshtien_contains_AlgoSearchImpl(items);
        Set<String> results = iAlgoSearch.fuzzySearch("dog", 2);

        assertEquals(results, new TreeSet<>(Arrays.asList("1", "4", "5", "6")));
    }
}
