package org.parser.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.parser.GitEventParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class GitEventParserImpl implements GitEventParser {

    private static final String PUSH_EVENT_VALUE = "PushEvent";
    private final Map<String, Map<String, Integer>> usersNGrams = new HashMap<>();
    private final ObjectMapper mapper;

    public GitEventParserImpl() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public List<List<String>> parse(String fileName) {
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                Event event = mapper.readValue(line, Event.class);
                if (PUSH_EVENT_VALUE.equals(event.type())) {
                    createNGrams(event);
                }
            }
            return getRowsForCSV();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createNGrams(Event event) {
        if (event == null || event.payload() == null || CollectionUtils.isEmpty(event.payload().commits())) return;

        event.payload().commits().stream()
                .filter(this::isEventValidForNGram)
                .forEach(this::createNGramsForAuthors);

        usersNGrams.forEach((k, v) -> {
            Map<String, Integer> sortedMap = v.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(5)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
            usersNGrams.put(k, sortedMap);
        });
    }

    public boolean isEventValidForNGram(Event.Commit commit) {
        return commit != null &&
                commit.author() != null &&
                StringUtils.isNotBlank(commit.author().name()) &&
                StringUtils.isNotBlank(commit.message());
    }

    public void createNGramsForAuthors(Event.Commit commit) {
        String authorName = commit.author().name();
        Map<String, Integer> authorNGram = usersNGrams.computeIfAbsent(authorName, s -> new HashMap<>());

        String preparedMessage = prepareMessageForNGram(commit.message());
        String[] words = preparedMessage.split(" ");

        for (int i = 0; i < words.length - 2; i++) {
            String nGram = words[i] + " " + words[i + 1] + " " + words[i + 2];
            authorNGram.put(nGram, authorNGram.getOrDefault(nGram, 0) + 1);
        }
    }

    public String prepareMessageForNGram(String message) {
        return message.toLowerCase()
                .replaceAll("[.:\\-\n]", "")
                .replaceAll("[\\s+\\[\\]()]+", " ");
    }

    public List<List<String>> getRowsForCSV() {
        List<List<String>> rows = new ArrayList<>();

        usersNGrams.forEach((k, ngramMap) -> {
            List<String> row = new ArrayList<>();
            row.add(k);
            row.addAll(ngramMap.keySet());
            rows.add(row);
        });

        return rows;
    }
}
