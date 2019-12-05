package com.olimp;

import java.util.stream.IntStream;

/**
 * Write a program that will calculate the number of trailing zeros in a factorial of a given number.
 *
 * N! = 1 * 2 * 3 * ... * N
 *
 * Be careful 1000! has 2568 digits...
 *
 * Examples
 * zeros(6) = 1
 * # 6! = 1 * 2 * 3 * 4 * 5 * 6 = 720 --> 1 trailing zero
 *
 * zeros(12) = 2
 * # 12! = 479001600 --> 2 trailing zeros
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(Solution.zeros(12));
    }

    public static class Solution {
        public static int zeros(int n) {
            int max5Power = (int) (Math.log(n) / Math.log(5));
            return IntStream.range(1, max5Power + 1).map(i -> (int) (n/Math.pow(5, i))).sum();
        }
    }

}
