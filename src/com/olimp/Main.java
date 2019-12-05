package com.olimp;

import java.util.stream.IntStream;

/**
 *
 *Implement a function that receives two IPv4 addresses, and returns the number of addresses between them (including the first one, excluding the last one).
 *
 * All inputs will be valid IPv4 addresses in the form of strings. The last address will always be greater than the first one.
 *
 * Examples
 * ips_between("10.0.0.0", "10.0.0.50")  ==   50
 * ips_between("10.0.0.0", "10.0.1.0")   ==  256
 * ips_between("20.0.0.10", "20.0.1.0")  ==  246
 *
 */

public class Main {

    public static void main(String[] args) {
        long count = CountIPAddresses.ipsBetween("10.0.0.0", "20.0.1.1");
        System.out.println(count);
    }

    public static class CountIPAddresses {

        public static long ipsBetween(String start, String end) {
            String[] starts = start.split("\\.");
            String[] ends = end.split("\\.");
            return IntStream.range(0, 4)
                    .mapToLong(i -> (long) ((Integer.parseInt(ends[i]) - Integer.parseInt(starts[i])) * Math.pow(256, 3 - i)))
                    .sum();
        }
    }

}
