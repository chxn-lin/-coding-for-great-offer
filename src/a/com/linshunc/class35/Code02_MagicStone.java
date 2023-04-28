package a.com.linshunc.class35;

import java.util.Arrays;

public class Code02_MagicStone {

    public static void main(String[] args) {
        int[][] stones = {
                {0, 1, 2},
                {1, 1, 2},
                {0, 4, 5},
                {0, 6, 5},
                {0, 9, 5},
                {0, 2, 5},
                {0, 5, 5},
                {2, 2, 3},
        };
        System.out.println(class35.Code02_MagicStone.minCost(stones));
        System.out.println(minCost(stones));
    }

    public static int minCost(int[][] stones) {
        if (stones.length % 2 == 1) {
            return -1;
        }
        int needRed = stones.length / 2;
        int needBlue = stones.length / 2;
        int cost = 0;
        for (int[] stone : stones) {
            if (stone[0] == 1) {
                needRed--;
            }
            else if (stone[0] == 2) {
                needBlue--;
            }
            else {
                cost += stone[1];
            }
        }
        if (needBlue < 0 || needRed < 0) {
            return -1;
        }
        Arrays.sort(stones, (a, b) -> a[0] != b[0] ? (a[0] - b[0]) : ((a[2] - a[1]) - (b[2] - b[1] )));
        for (int i = 1; i <= needBlue; i++) {
            cost += stones[i - 1][2] - stones[i - 1][1];
        }

        return cost;
    }

}
