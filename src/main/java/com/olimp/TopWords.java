package com.olimp;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TopWords {

    public static List<String> top3(String s) {
        ArrayList<String> result = new ArrayList<>();
        Map<String, Integer> wordCountMap = new HashMap<>();

        s = s.toLowerCase().replaceAll("[^a-z'\\s]+|\\d+| '+ ", " ");

        Pattern pattern = Pattern.compile("[a-z']+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String word = matcher.group();
            wordCountMap.merge(word, 1, Integer::sum);
        }

        wordCountMap
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(3)
                .forEach(entry->result.add(entry.getKey()));

        return result;
    }
}