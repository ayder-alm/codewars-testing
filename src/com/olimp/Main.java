package com.olimp;

/**
 * Here's a seemingly simple challenge. We're giving you a class called bagel, exactly as it appears below. All it really does is return an int, specifically 3.
 *
 * public class Bagel {
 *     public final int getValue() {
 *         return 3;
 *     }
 * }
 * The catch? For the solution, we're testing that the result is equal to 4. But as a little hint, the solution to this Kata is (almost) exactly the same as the example test cases.
 *
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("BagelSolver.getBagel().getValue() is not 3 but " + BagelSolver.getBagel().getValue());
    }

}
