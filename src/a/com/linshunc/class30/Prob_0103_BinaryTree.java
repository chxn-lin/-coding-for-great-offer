package a.com.linshunc.class30;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Prob_0103_BinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            LinkedList<TreeNode> list = new LinkedList();
            list.add(root);
            int size = list.size();
            boolean needReserve = false;
            while (size > 0) {
                List<Integer> curList = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = list.pollFirst();
                    curList.add(cur.val);
                    if (cur.left != null) {
                        list.addLast(cur.left);
                    }
                    if (cur.right != null) {
                        list.addLast(cur.right);
                    }
                }
                if (needReserve) {
                    curList = reserve(curList);
                }
                res.add(curList);

                size = list.size();
                needReserve = !needReserve;
            }
        }
        return res;
    }

    private static List<Integer> reserve(List<Integer> curList) {
        Collections.reverse(curList);
        return curList;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        node5.right = node9;

        List<List<Integer>> lists = zigzagLevelOrder(node1);
        System.out.println(lists);
    }

}
