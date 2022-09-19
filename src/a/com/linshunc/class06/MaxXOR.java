package a.com.linshunc.class06;

import static class06.Code01_MaxXOR.*;

public class MaxXOR {

    public static int method1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int res = Integer.MIN_VALUE;

        int[] eorArr = new int[arr.length];
        eorArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            eorArr[i] = arr[i] ^ eorArr[i - 1];
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                // 以i位置结尾，从j开始，最大的异或和是多少
                int cur = j == 0 ? eorArr[i] : eorArr[i] ^ eorArr[j - 1];
                res = Math.max(res, cur);
            }
        }

        return res;
    }

    public static class Node {
        Node[] next = new Node[2];
    }

    public static class PreTree {
        Node head;

        public PreTree() {
            head = new Node();
        }

        public void add(int num){
            Node cur = head;
            for (int i = 31; i >= 0; i--) {
                // 获取第i位的数字
                int n = (num >> i) & 1;
                if (cur.next[n] == null) {
                    cur.next[n] = new Node();
                }
                cur = cur.next[n];
            }
        }

        // 拿到和给定数组异或和最大的数字，返回异或和之后的结果
        public int getMaxNumWithExit(int num){
            int existNum = 0;

            // 第31位，符号位，尽可能得到的结果是0
            Node cur = head;
            int n = (num >> 31) & 1;
            if (cur.next[n] == null) {
                n = n ^ 1;
            }
            existNum = (n << 31) | existNum;
            cur = cur.next[n];

            // 其余位置尽可能不一样
            for (int i = 30; i >= 0; i--) {
                n = (num >> i) & 1;
                n = n ^ 1;
                if (cur.next[n] == null) {
                    n = n ^ 1;
                }
                existNum = (n << i) | existNum;
                cur = cur.next[n];
            }

            return existNum ^ num;
        }
    }

    public static int method2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        PreTree tree = new PreTree();
        tree.add(0);

        int pre = 0;
        for (int i = 0; i < arr.length; i++) {
            pre = arr[i] ^ pre;
            res = Math.max(tree.getMaxNumWithExit(pre), res);
            tree.add(pre);
        }

        return res;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int comp = method2(arr);
            int res = maxXorSubarray2(arr);
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
