package com.olimp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class Main {

    public static void main(String[] args) {
        Tests();
    }

    private static void Tests() {
        int[] a = new int[]{121, 144, 19, 161, 19, 144, 19, 11};
        int[] b = new int[]{121, 14641, 20736, 361, 25921, 361, 20736, 361};
        assertEquals(true, AreSame.comp(a, b));
        a = new int[]{12, 11, 11};
        b = new int[]{121, 144, 121};
        assertEquals(true, AreSame.comp(a, b));
    }

    public static class AreSame {

        public static boolean comp(int[] a, int[] b) {
            if (a == null || b == null || a.length != b.length) {
                return false;
            }
            HashMap<Integer, Integer> squares = new HashMap<>(a.length);
            for (int num : a) {
                Integer square = num * num;
                Integer count = squares.get(square);
                squares.put(square, count != null ? count + 1 : 1);
            }
            for (int square : b) {
                Integer count = squares.get(square);
                if (count == null || count == 0) {
                    return false;
                } else {
                    squares.put(square, count - 1);
                }
            }
            return true;
        }
    }

}
