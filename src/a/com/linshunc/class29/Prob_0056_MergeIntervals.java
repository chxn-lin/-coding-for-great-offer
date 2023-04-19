package a.com.linshunc.class29;

import class29.Problem_0056_MergeIntervals;

import java.util.Arrays;

public class Prob_0056_MergeIntervals {

    public static int[][] merge(int[][] intervals) {
        if (intervals.length < 1) {
            return new int[0][0];
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int size = 0;
        int[][] res = new int[intervals.length][2];
        int L = intervals[0][0];
        int R = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int curL = intervals[i][0];
            int curR = intervals[i][1];
            if (curL > R) {
                // 结算上一个的范围
                res[size++] = new int[]{L, R};
                L = curL;
                R = curR;
            }
            else {
                R = Math.max(R, curR);
            }
        }
        res[size++] = new int[]{L, R};
        return Arrays.copyOf(res, size);
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {0, 12},
                {4, 12},
                {18, 26},
        };
        int[][] merge = merge(intervals);
        int[][] merge2 = Problem_0056_MergeIntervals.merge(intervals);
        for (int i = 0; i < merge.length; i++) {
            System.out.println(Arrays.toString(merge[i]));
        }
        System.out.println("=====");
        for (int i = 0; i < merge2.length; i++) {
            System.out.println(Arrays.toString(merge2[i]));
        }
    }

}
