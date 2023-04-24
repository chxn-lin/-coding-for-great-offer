package a.com.linshunc.class32;

import class32.Problem_0152_MaximumProductSubarray;

import java.math.BigDecimal;

public class Prob_0152_MaximumSub {

    public static double max(double[] arr) {
        if (arr.length < 1) {
            return 0;
        }
        double[] dpMax = new double[arr.length];
        double[] dpMin = new double[arr.length];
        dpMax[arr.length - 1] = arr[arr.length - 1];
        dpMin[arr.length - 1] = arr[arr.length - 1];

        for (int i = arr.length - 2; i >= 0; i--) {
            double max = arr[i];
            double min = arr[i];
            max = Math.max(Math.max(max * dpMin[i + 1], max * dpMax[i + 1]), max);
            min = Math.min(Math.min(min * dpMin[i + 1], min * dpMax[i + 1]), min);
            dpMax[i] = max;
            dpMin[i] = min;
        }

        double res = dpMax[0];
        for (int i = 1; i < dpMax.length; i++) {
            res = Math.max(dpMax[i], res);
        }
        return res;
    }

    public static void main(String[] args) {
        double[] arr = {123, 22, -19, 2, 4, 0.4, -2, -3, 10};
        BigDecimal bigDecimal = new BigDecimal("" + max(arr));

        System.out.println(bigDecimal);
        System.out.println(Problem_0152_MaximumProductSubarray.max(arr));
    }

}
