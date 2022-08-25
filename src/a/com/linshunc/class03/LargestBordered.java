package a.com.linshunc.class03;

import class03.Code03_Largest1BorderedSquare;

public class LargestBordered {

    public static int largest1BorderedSquare(int[][] m) {
        if (m == null || m.length < 1) {
            return 0;
        }
        int res = 0;

        int[][] right = new int[m.length][m[0].length];
        int[][] down = new int[m.length][m[0].length];
        int pre = 0;
        for (int i = 0; i < m.length; i++) {
            pre = 0;
            for (int j = m[0].length - 1; j >= 0; j--) {
                if (m[i][j] == 0) {
                    pre = 0;
                } else {
                    pre++;
                }
                right[i][j] = pre;
            }
        }
        for (int j = 0; j < m[0].length; j++) {
            pre = 0;
            for (int i = m.length - 1; i >= 0; i--) {
                if (m[i][j] == 0) {
                    pre = 0;
                } else {
                    pre++;
                }
                down[i][j] = pre;
            }
        }
        int maxSize = Math.min(m.length, m[0].length);
        while (maxSize > 0) {
            if (hasSize(right, down, maxSize)) {
                res = maxSize;
                break;
            }
            maxSize--;
        }


        return res * res;
    }

    private static boolean hasSize(int[][] right, int[][] down, int size) {
        for (int i = 0; i <=  right.length - size; i++) {
            for (int j = 0; j <= right[0].length - size; j++) {
                if (right[i][j] >= size
                    && down[i][j] >= size
                    && right[i + size - 1][j] >= size
                    && down[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1,0,1,1,1,1},
                {1,1,1,1,1,1},
                {1,0,1,0,1,1},
                {1,0,1,0,0,1},
                {0,1,1,1,1,1},
                {1,1,1,1,0,1}
        };
        System.out.println(largest1BorderedSquare(arr));
        System.out.println(Code03_Largest1BorderedSquare.largest1BorderedSquare(arr));
    }

}
