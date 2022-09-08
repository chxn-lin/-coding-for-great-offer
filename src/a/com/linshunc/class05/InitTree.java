package a.com.linshunc.class05;

import java.util.Stack;

public class InitTree {

    // 不用提交这个类
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode bstFromPreorder1(int[] pre) {
        if (pre == null || pre.length < 1) {
            return null;
        }
        return process(pre, 0, pre.length - 1);
    }

    private static TreeNode process(int[] pre, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode head = new TreeNode(pre[start]);
        // 找到最后一个比他小的位置
        int mid = start;
        for (int i = start + 1; i <= end; i++) {
            if (pre[start] < pre[i]) {
                break;
            }
            mid = i;
        }

        head.left = process(pre, start + 1, mid);
        head.right = process(pre, mid + 1, end);
        return head;
    }

    public static TreeNode bstFromPreorder2(int[] pre) {
        if (pre == null || pre.length < 1) {
            return null;
        }
        int len = pre.length;
        int[] nearBig = new int[len];
        for (int i = 0; i < len; i++) {
            nearBig[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && pre[stack.peek()] < pre[i]) {
                nearBig[stack.pop()] = i;
            }
            stack.push(i);
        }
        return process2(pre, 0, len - 1, nearBig);
    }

    private static TreeNode process2(int[] pre, int start, int end, int[] nearBig) {
        if (start > end) {
            return null;
        }
        TreeNode head = new TreeNode(pre[start]);
        // 找到最后一个比他小的位置
        int mid = nearBig[start] - 1;
        if (mid == -2) {
            head.left = process2(pre, start + 1, end, nearBig);
        } else {
            head.left = process2(pre, start + 1, mid, nearBig);
            head.right = process2(pre, mid + 1, end, nearBig);
        }
        return head;
    }

    public static void main(String[] args) {
        int[] pre = {5, 4, 3, 2, 1};
        TreeNode node1 = bstFromPreorder1(pre);
        TreeNode node2 = bstFromPreorder2(pre);
        System.out.println(123);
    }

}
