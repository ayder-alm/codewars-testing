package com.olimp;

import java.util.ArrayList;

import static com.olimp.Main.Interval.sumIntervals;
import static org.junit.Assert.assertEquals;

/**
 * Write a function called sumIntervals/sum_intervals() that accepts an array of intervals, and returns the sum of all
 * the interval lengths. Overlapping intervals should only be counted once.
 * <p>
 * Intervals
 * Intervals are represented by a pair of integers in the form of an array. The first value of the interval will always
 * be less than the second value. Interval example: [1, 5] is an interval from 1 to 5. The length of this interval is 4.
 * <p>
 * Overlapping Intervals
 * List containing overlapping intervals:
 * [
 * [1,4],
 * [7, 10],
 * [3, 5]
 * ]
 * The sum of the lengths of these intervals is 7. Since [1, 4] and [3, 5] overlap, we can treat the interval as [1, 5],
 * which has a length of 4.
 */
public class Main {

    public static void main(String[] args) {
        Tests();
    }

    private static void Tests() {
        assertEquals(0, sumIntervals(null));
        assertEquals(0, sumIntervals(new int[][]{}));
        assertEquals(0, sumIntervals(new int[][]{{4, 4}, {6, 6}, {8, 8}}));

        assertEquals(9, sumIntervals(new int[][]{{1, 2}, {6, 10}, {11, 15}}));
        assertEquals(11, sumIntervals(new int[][]{{4, 8}, {9, 10}, {15, 21}}));
        assertEquals(7, sumIntervals(new int[][]{{-1, 4}, {-5, -3}}));
        assertEquals(78, sumIntervals(new int[][]{{-245, -218}, {-194, -179}, {-155, -119}}));

        assertEquals(54, sumIntervals(new int[][]{{1, 2}, {2, 6}, {6, 55}}));
        assertEquals(23, sumIntervals(new int[][]{{-2, -1}, {-1, 0}, {0, 21}}));
        assertEquals(7, sumIntervals(new int[][]{{1, 4}, {7, 10}, {3, 5}}));
        assertEquals(6, sumIntervals(new int[][]{{5, 8}, {3, 6}, {1, 2}}));
        assertEquals(19, sumIntervals(new int[][]{{1, 5}, {10, 20}, {1, 6}, {16, 19}, {5, 11}}));

        assertEquals(13, sumIntervals(new int[][]{{2, 5}, {-1, 2}, {-40, -35}, {6, 8}}));
        assertEquals(1234, sumIntervals(new int[][]{{-7, 8}, {-2, 10}, {5, 15}, {2000, 3150}, {-5400, -5338}}));
        assertEquals(158, sumIntervals(new int[][]{{-101, 24}, {-35, 27}, {27, 53}, {-105, 20}, {-36, 26},}));
    }

//    testing: [3330, 3445], [-2181, 3591], [-8822, 6753], [-6047, -5493], [422, 8628], [-947, 7059], [-7510, 951], [-4354, 5703], [-6149, -3150], [-6037, -3403], [-5358, 4268], [-4825, 8803], [81, 1771], [-7160, 5413], [-9320, 2628], [-7350, 5112], [-4372, 708], [3689, 9761], [-6213, 7607], [-5880, 2277], [-1641, 9944]
//    expected:<19264> but was:<19081>


    public static class Interval {

        int length;
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
            length = end - start;
        }

        Interval(int[] arr) {
            this(arr[0], arr[1]);
        }

        public static int sumIntervals(int[][] intervals) {
            if (intervals == null || intervals.length == 0) {
                return 0;
            }
            ArrayList<Interval> intervalList = new ArrayList<>();
            //add all worthy intervals
            for (int[] intervalArr : intervals) {
                if (intervalArr != null && intervalArr.length == 2) {
                    int intervalLength = intervalArr[1] - intervalArr[0];
                    if (intervalLength > 0) {
                        intervalList.add(new Interval(intervalArr));
                    }
                }
            }
            if (intervalList.isEmpty()) {
                return 0;
            }
            //optimization to get overlaps faster
            intervalList.sort((o1, o2) -> o2.length - o1.length);

            boolean hasOverlappedIntervals;
            //combine all overlapped intervals while possible
            do {
                hasOverlappedIntervals = false;
                for (int i = 0; i < intervalList.size(); i++) {
                    for (int j = i + 1; j < intervalList.size(); j++) {
                        Interval result = combine(intervalList.get(i), intervalList.get(j));
                        if (result != null) {
                            intervalList.set(i, result);
                            intervalList.remove(j);
                            hasOverlappedIntervals = true;
                            break;
                        }
                    }
                    if (hasOverlappedIntervals) {
                        break;
                    }
                }
        } while(hasOverlappedIntervals);
        //collect all interval lengths
            return intervalList.stream().mapToInt(interval ->interval.length).sum();
    }

    private static Interval combine(Interval bigInterval, Interval smallInterval) {
        if (bigInterval.length < smallInterval.length) {
            Interval temp = smallInterval;
            smallInterval = bigInterval;
            bigInterval = temp;
        }
        if (smallInterval.start <= bigInterval.start && smallInterval.end >= bigInterval.start) {
            return new Interval(smallInterval.start, bigInterval.end);
        }
        if (smallInterval.start <= bigInterval.end && smallInterval.end >= bigInterval.end) {
            return new Interval(bigInterval.start, smallInterval.end);
        }
        if (smallInterval.start >= bigInterval.start && smallInterval.end <= bigInterval.end) {
            return new Interval(bigInterval.start, bigInterval.end);
        }
        return null;
    }
}

}
