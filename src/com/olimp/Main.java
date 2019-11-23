package com.olimp;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class Main {
    final static boolean USE_LOCAL_FILE = true;

    public static void main(String[] args) throws java.lang.Exception {
        InputStream is;
        if (USE_LOCAL_FILE) {
            is = new FileInputStream("test.txt");
        } else {
            is = System.in;
        }
        Scanner in = new Scanner(is);
        final int TEST_CASE_NUM = in.nextInt();
        String[] testCases = new String[TEST_CASE_NUM];
        in.nextLine();
        for (int i = 0; i < TEST_CASE_NUM; i++) {
            testCases[i] = in.nextLine();
        }
        for (int i = 0; i < TEST_CASE_NUM; i++) {
            String w = testCases[i];
//            System.out.printf("direct = %s is inverse = %s", w, "" + inverseDna(w));
            if (i < TEST_CASE_NUM - 1) {
                // add empty line after each test case
                System.out.println();
            }
        }
        Tests();
    }

    public static String makeReadable(int seconds) {
        int ss = seconds % 60;
        int mm = seconds / 60;
        int hh = mm / 60;
        mm -= hh * 60;
        return String.format("%02d:%02d:%02d" , hh, mm, ss);
    }

    public static void Tests() {
        assertEquals("makeReadable(0)", "00:00:00", makeReadable(0));
        assertEquals("makeReadable(5)", "00:00:05", makeReadable(5));
        assertEquals("makeReadable(60)", "00:01:00", makeReadable(60));
        assertEquals("makeReadable(86399)", "23:59:59", makeReadable(86399));
        assertEquals("makeReadable(359999)", "99:59:59", makeReadable(359999));
    }

}
