package a.com.linshunc.class06;

import class06.Code02_MaximumXorOfTwoNumbersInAnArray;

import static class06.Code01_MaxXOR.*;

public class MaxXOR2Num {

    public static int method2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        MaxXOR.PreTree tree = new MaxXOR.PreTree();
        tree.add(arr[0]);

        int pre = 0;
        for (int i = 1; i < arr.length; i++) {
            pre = arr[i];
            res = Math.max(tree.getMaxNumWithExit(pre), res);
            tree.add(pre);
        }

        return res;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int comp = method2(arr);
            int res = Code02_MaximumXorOfTwoNumbersInAnArray.findMaximumXOR(arr);
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
