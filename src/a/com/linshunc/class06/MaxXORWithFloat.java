package a.com.linshunc.class06;

import class06.Code03_MaximumXorWithAnElementFromArray;

import java.util.Arrays;

public class MaxXORWithFloat {

    public static int[] maximizeXor(int[] nums, int[][] queries) {
        PreTree tree = new PreTree();
        for (int i = 0; i < nums.length; i++) {
            tree.add(nums[i]);
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = tree.getMaxNumWithExitFloat(queries[i][0], queries[i][1]);
        }
        return ans;
    }

    public static class Node {
        Node[] next = new Node[2];
        int minNum;

        public Node() {
            minNum = Integer.MAX_VALUE;
        }
    }

    public static class PreTree {
        Node head;

        public PreTree() {
            head = new Node();
        }

        public void add(int num) {
            Node cur = head;
            cur.minNum = Math.min(num, cur.minNum);
            for (int i = 30; i >= 0; i--) {
                // 获取第i位的数字
                int n = (num >> i) & 1;
                if (cur.next[n] == null) {
                    cur.next[n] = new Node();
                }
                cur = cur.next[n];
                cur.minNum = Math.min(num, cur.minNum);
            }
        }

        // 拿到和给定数组异或和最大的数字，返回异或和之后的结果
        public int getMaxNumWithExitFloat(int num, int maxNum) {
            int existNum = 0;

            // 第31位，符号位，尽可能得到的结果是0
            Node cur = head;
            int n = 0;

            // 其余位置尽可能不一样
            for (int i = 30; i >= 0; i--) {
                if (cur.minNum > maxNum) {
                    return -1;
                }
                n = (num >> i) & 1;
                n = n ^ 1;
                if (cur.next[n] == null || cur.next[n].minNum > maxNum) {
                    n = n ^ 1;
                }
                existNum = (n << i) | existNum;
                cur = cur.next[n];
            }

            return existNum ^ num;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,4,8,16,32};
        int[][] queries = {
                {1023,9},
                {23,9},
                {33,653},
                {23,165},
                {2332,4},
                {3513,216},
                {3,4},
                {232332,234},
                {3,423},
                {642,4},
        };
        int[] res1 = maximizeXor(arr, queries);
        int[] res2 = Code03_MaximumXorWithAnElementFromArray.maximizeXor(arr, queries);
        printArr(res1);
        printArr(res2);

        System.out.println(Arrays.equals(res1, res2));
    }

    private static void printArr(int[] arr){
        System.out.print("[");
        for (int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println("]");
        System.out.println();
    }

}
