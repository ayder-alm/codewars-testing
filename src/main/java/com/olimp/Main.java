package com.olimp;

import static java.lang.System.out;

/**
 * Create an endless stream of prime numbers - a bit like IntStream.of(2, 3, 5, 7, 11, 13, 17), but infinite.
 * The stream must be able to produce a million primes in a few seconds.
 */

public class Main {

    public static void main(String[] args) {
        Primes.stream().limit(20).forEach(out::println);

        long start = System.currentTimeMillis();
        Primes.stream().limit(2000_000).forEach(value -> {});
        long elapsed = System.currentTimeMillis() - start;

        out.println("Spent time = " + elapsed);
    }

}
