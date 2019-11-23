package com.olimp;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * Given an n x n array, return the array elements arranged from outermost elements to the middle element, traveling clockwise.
     *
     * array = [[1,2,3],
     *          [4,5,6],
     *          [7,8,9]]
     * snail(array) #=> [1,2,3,6,9,8,7,4,5]
     * For better understanding, please follow the numbers of the next array consecutively:
     *
     * array = [[1,2,3],
     *          [8,9,4],
     *          [7,6,5]]
     * snail(array) #=> [1,2,3,4,5,6,7,8,9]
     */
    public static void main(String[] args) {
        Tests();
    }

    private static void Tests() {
        int[][] array = {{1,  2,  3, 4},
                        {12, 13, 14, 5},
                        {11, 16, 15, 6},
                        {10,  9,  8, 7}};

        int[][] array2 = {{1, 2,  3,  4,  5},
                         {16, 17, 18, 19, 6},
                         {15, 24, 25, 20, 7},
                         {14, 23, 22, 21, 8},
                         {13, 12, 11, 10, 9}};

        int[][] array3 = {{1, 2,  3,  4,  5,  6},
                         {20, 21, 22, 23, 24, 7},
                         {19, 32, 33, 34, 25, 8},
                         {18, 31, 36, 35, 26, 9},
                         {17, 30, 29, 28, 27, 10},
                         {16, 15, 14, 13, 12, 11}};

        int[] result = Snail.snail(array3);
        for (int i : result) {
            System.out.print(i + ", ");
        }
    }

    public static class Snail {

        public static int[] snail(int[][] array) {
            if (array == null) {
                return null;
            }
            int dimen = array.length;
            if (dimen == 1) {
                return array[0];
            }
            List<Integer> resultList = unwind(array, dimen, 0, 0);
            return resultList.stream().mapToInt(integer -> integer).toArray();
        }

        private static List<Integer> unwind(int[][] array, int dimen, int row, int col) {
            ArrayList<Integer> result = new ArrayList<>();
            if (dimen == 1) {
                result.add(array[col][row]);
                return result;
            }
            if (dimen == 2) {
                result.add(array[col][row]);
                result.add(array[row][col+1]);
                result.add(array[row+1][col + 1]);
                result.add(array[row+1][col]);
                return result;
            }
            //top row
            for (int i = 0; i < dimen; i++) {
                result.add(array[row][col + i]);
            }
            //between top row and bottom row (right column)
            for (int i = 1; i < dimen - 1; i++) {
                result.add(array[row + i][col + dimen - 1]);
            }
            //bottom row
            for (int i = dimen - 1; i >= 0 ; i--) {
                result.add(array[row + dimen - 1][col+ i]);
            }
            //between top row and bottom row (left column)
            for (int i = dimen - 2; i > 0; i--) {
                result.add(array[row + i][col]);
            }

            List<Integer> innerUnwindResult = unwind(array, dimen - 2, row + 1, col + 1);
            result.addAll(innerUnwindResult);
            return result;
        }


    }

}
