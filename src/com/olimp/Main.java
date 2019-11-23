package com.olimp;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class Main {

    /**
     * The goal of this exercise is to convert a string to a new string where each character in the new string is "("
     * if that character appears only once in the original string, or ")" if that character appears more than once in
     * the original string. Ignore capitalization when determining if a character is a duplicate.
     *
     * Examples
     * "din"      =>  "((("
     * "recede"   =>  "()()()"
     * "Success"  =>  ")())())"
     * "(( @"     =>  "))(("
     */

    public static void main(String[] args) {
        Tests();
    }

    private static void Tests() {
        assertEquals(")()())()(()()(", DuplicateEncoder.encode("Prespecialized"));
        assertEquals("))))())))", DuplicateEncoder.encode("   ()(   "));
    }

    public static class DuplicateEncoder {
        private static char REP_CH = (char) -1;
        static String encode(String word){
            if (word.length() == 0)
                return "";
            word = word.toLowerCase();

            HashSet<Character> repeatedChars = new HashSet<>();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (word.lastIndexOf(String.valueOf(ch)) != i) {
                    repeatedChars.add(ch);
                }
            }
            for (int i = 0; i < word.length(); i++) {
                result.append(repeatedChars.contains(word.charAt(i)) ? ')' : '(');
            }
            return result.toString();
        }
    }

}
