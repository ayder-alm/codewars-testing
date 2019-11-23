package com.olimp;

import java.util.*;
import java.lang.*;

@SuppressWarnings("ALL")
class Main_test {
    public static void main (String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);
        final int TEST_CASE_NUM = in.nextInt();
        int testCase;
        for (int i = 0; i < TEST_CASE_NUM; i++) {
            testCase = in.nextInt();
            System.out.print(getFactorialZeroCount(testCase));
            if (i < TEST_CASE_NUM - 1) { // add empty line after each test case
                System.out.println();
            }
        }
    }
        private static int getFactorialZeroCount(int num) {
            if (num < 5) return 0;
            int divizor = 5;
            int result = 0;
            while (divizor <= num) {
                result += num/divizor;
                divizor *=5;
            }
            return result;
        }
}