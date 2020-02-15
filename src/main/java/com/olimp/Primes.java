package com.olimp;

import java.util.stream.IntStream;

public class Primes {

    private static boolean isPrime(int primeCandidate) {
        //primes are always either 6k+1 or 6k-1
        //this also eliminates even numbers.
        //Fails for 2 and 3 for performance reasons
        int modSix = primeCandidate % 6;
        if (modSix != 1 && modSix != 5) {
            return false;
        }
        int greatestDivisor = (int) Math.sqrt(primeCandidate); //greatest divisor of a non prime number is less than its square root

        for (int divisor = 3; divisor <= greatestDivisor; divisor += 2) { //check only odd divisors to speed up
            if (primeCandidate % divisor == 0) {
                return false;
            }
        }
        return true; //no divisors found - number is prime
    }

    public static IntStream stream() {
        IntStream primeStream = IntStream
                .iterate(5, p -> p + 2) //need only odd numbers
                .filter(Primes::isPrime);
        return IntStream.concat(IntStream.of(2, 3), primeStream);
    }
}
