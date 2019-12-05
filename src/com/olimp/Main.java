package com.olimp;

import java.util.HashMap;

/**
 * we want to convert a string into an integer. The strings simply represent the numbers in words.
 * <p>
 * Examples:
 * <p>
 * "one" => 1
 * "twenty" => 20
 * "two hundred forty-six" => 246
 * "seven hundred eighty-three thousand nine hundred and nineteen" => 783919
 * Additional Notes:
 * <p>
 * The minimum number is "zero" (inclusively)
 * The maximum number, which must be supported is 1 million (inclusively)
 * The "and" in e.g. "one hundred and twenty-four" is optional, in some cases it's present and in others it's not
 * All tested numbers are valid, you don't need to validate them
 */
public class Main {

    public static void main(String[] args) {
        Tests();
    }

    private static void Tests() {
        String num = "seven hundred eighty-three      thousand nine  hundred  and  nineteen";
        System.out.println(Parser.parseInt(num));
    }

    public static class Parser {
        private static HashMap<String, Integer> NUMBERS_DICT = new HashMap<>();
        static {
            NUMBERS_DICT.put("one", 1);
            NUMBERS_DICT.put("two", 2);
            NUMBERS_DICT.put("three", 3);
            NUMBERS_DICT.put("four", 4);
            NUMBERS_DICT.put("five", 5);
            NUMBERS_DICT.put("six", 6);
            NUMBERS_DICT.put("seven", 7);
            NUMBERS_DICT.put("eight", 8);
            NUMBERS_DICT.put("nine", 9);
            NUMBERS_DICT.put("ten", 10);
            NUMBERS_DICT.put("eleven", 11);
            NUMBERS_DICT.put("twelve", 12);
            NUMBERS_DICT.put("thirteen", 13);
            NUMBERS_DICT.put("fourteen", 14);
            NUMBERS_DICT.put("fifteen", 15);
            NUMBERS_DICT.put("sixteen", 16);
            NUMBERS_DICT.put("seventeen", 17);
            NUMBERS_DICT.put("eighteen", 18);
            NUMBERS_DICT.put("nineteen", 19);
            NUMBERS_DICT.put("twenty", 20);
            NUMBERS_DICT.put("thirty", 30);
            NUMBERS_DICT.put("forty", 40);
            NUMBERS_DICT.put("fifty", 50);
            NUMBERS_DICT.put("sixty", 60);
            NUMBERS_DICT.put("seventy", 70);
            NUMBERS_DICT.put("eighty", 80);
            NUMBERS_DICT.put("ninety", 90);
            NUMBERS_DICT.put("zero", 0);
            NUMBERS_DICT.put("million", 1000_000);
            NUMBERS_DICT.put("onemillion", 1000_000);
            NUMBERS_DICT.put("", 1);
        }

        public static int parseInt(String numStr) {
            int result;
            String[] parts;
            //remove "and" and spaces because they're optional
            numStr = numStr.replaceAll(" and |\\s", "");
            //handle cases when we have thousands hundreds and less
            if (numStr.contains("thousand")) {
                parts = numStr.split("thousand");
                result = parseInt(parts[0]) * 1000;
                if (parts.length == 2) {
                    result += parseInt(parts[1]);
                }
            } else if (numStr.contains("hundred")) {
                parts = numStr.split("hundred");
                result = parseInt(parts[0]) * 100;
                if (parts.length == 2) {
                    result += parseInt(parts[1]);
                }
            } else if (numStr.contains("-")) {
                parts = numStr.split("-");
                result = parseInt(parts[0]);
                if (parts.length == 2) {
                    result += parseInt(parts[1]);
                }
            } else {
                result = NUMBERS_DICT.get(numStr);
            }
            return result;
        }
    }

}
