package a.com.linshunc.class29;

import java.util.Arrays;

public class Prob_0073_SetMatrixZeroes {

    public static void setZeroes1(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        boolean firstColumnNeedFormat = false;// 行
        boolean firstRowNeedFormat = false;// 列

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstRowNeedFormat = true;
                break;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstColumnNeedFormat = true;
                break;
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        // 写数据
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstColumnNeedFormat) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
        if (firstRowNeedFormat) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,2,5,2,0,1,2},
                {0,2,3,2,5,2,3,1,2},
                {1,2,3,2,5,2,5,1,2},
                {1,2,3,2,5,2,0,1,2},
                {1,2,3,2,5,2,4,1,2},
                {1,0,3,2,5,2,2,1,2},
        };
        setZeroes1(matrix);
    }

}
