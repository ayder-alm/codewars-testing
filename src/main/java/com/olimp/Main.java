package com.olimp;

/**
 * Define a regular expression which tests if a given string representing a binary number is divisible by 5.
 *
 * Examples:
 * // 5 divisible by 5
 * DivisibleByFive.pattern().matcher('101').matches() == true
 *
 * // 135 divisible by 5
 * DivisibleByFive.pattern().matcher('10000111').matches() == true
 *
 * // 666 not divisible by 5
 * DivisibleByFive.pattern().matcher('0000001010011010').matches() == false
 * Note:
 * This can be solved by creating a Finite State Machine that evaluates if a string representing
 * a number in binary base is divisible by given number.
 */

public class Main {

    public static void main(String[] args) {

    }
}
