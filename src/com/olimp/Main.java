package com.olimp;

import static org.junit.Assert.assertEquals;

public class Main {
    public static void main(String[] args) {
        Tests();
    }

    public static int sequence(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int currentSum;
        int currentPosition = 0;
        int maxSum = 0;
        while (currentPosition < arr.length){
            currentPosition = getFirstPositiveNumberIndex(arr, currentPosition);
            if (currentPosition == -1) {
                break;
            }
            currentSum = arr[currentPosition];
            while (currentSum > 0) {
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
                currentPosition ++;
                if (currentPosition >= arr.length) {
                    break;
                }
                currentSum += arr[currentPosition];
            }
        }
        return maxSum;
    }

    private static int getFirstPositiveNumberIndex(int[] arr, int startPos) {
        for (int i = startPos; i < arr.length; i++) {
            if (arr[i] > 0) {
                return i;
            }
        }
        return -1;
    }


    public static void Tests() {
            assertEquals("Empty arrays should have a max of 0", 0, sequence(new int[]{}));
            assertEquals("Example array should have a max of 6", 6, sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
