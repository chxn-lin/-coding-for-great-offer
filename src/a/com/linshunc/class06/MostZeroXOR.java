package a.com.linshunc.class06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static class06.Code04_MostXorZero.*;

public class MostZeroXOR {

    // 暴力解
    public static int method1(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] xorArr = new int[arr.length];
        xorArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            xorArr[i] = arr[i] ^ xorArr[i - 1];
        }

        return process(xorArr, new ArrayList<Integer>(), 0);
    }

    private static int process(int[] xorArr, List<Integer> list, int index) {
        int res;
        if (index == xorArr.length) {// 到最后一个了，计算结果
            res = getNum(list, xorArr);
        } else {
            int p1 = process(xorArr, list, index + 1);
            list.add(index);
            int p2 = process(xorArr, list, index + 1);
            list.remove(list.size() - 1);
            res = Math.max(p1, p2);
        }
        return res;
    }

    private static int getNum(List<Integer> list, int[] xorArr) {
        int res = 0;
        if (list.isEmpty()) {
            res = xorArr[xorArr.length - 1] == 0 ? 1 : 0;
        } else {
            int lastEnd = 0;
            int end = list.get(0);
            res += xorArr[end] == 0 ? 1 : 0;
            for (int i = 1; i < list.size(); i++) {
                lastEnd = end;
                end = list.get(i);
                res += xorArr[end] == xorArr[lastEnd] ? 1 : 0;
            }
            if (list.get(list.size() - 1) != xorArr.length - 1) {
                // 最后一个没有分界
                res += (xorArr[list.get(list.size() - 1)] ^ xorArr[xorArr.length - 1])
                        == 0 ? 1 : 0;
            }
        }

        return res;
    }

    // 最优解
    public static int method2(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] dp = new int[arr.length];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) {
                int index = map.get(xor);
                dp[i] = index == -1 ? 1 : dp[index] + 1;
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i - 1], dp[i]);
            }
            map.put(xor, i);
        }

        return dp[arr.length - 1];
    }

    public static void main(String[] args) {


        int testTime = 10000;
        int maxSize = 12;
        int maxValue = 5;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = mostXor(arr);
            int comp = method2(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
