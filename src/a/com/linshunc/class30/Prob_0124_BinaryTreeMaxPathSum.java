package a.com.linshunc.class30;

import class30.Problem_0124_BinaryTreeMaximumPathSum;
import class30.Problem_0124_BinaryTreeMaximumPathSum.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Prob_0124_BinaryTreeMaxPathSum {

    // 最大路径和
    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxValue;
    }

    private static Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info left = process(root.left);
        Info right = process(root.right);

        int withCurMaxValue = root.val;
        int maxValue = root.val;
        // 分析情况
        // 包含当前节点
        if (left != null) {
            withCurMaxValue = Math.max(withCurMaxValue, left.curHeadMaxValue + root.val);
        }
        if (right != null) {
            withCurMaxValue = Math.max(withCurMaxValue, right.curHeadMaxValue + root.val);
        }
        maxValue = Math.max(maxValue, withCurMaxValue);
        if (left != null) {
            maxValue = Math.max(maxValue, left.maxValue);
        }
        if (right != null) {
            maxValue = Math.max(maxValue, right.maxValue);
        }
        if (left != null && right != null) {
            maxValue = Math.max(maxValue, left.curHeadMaxValue + root.val + right.curHeadMaxValue);
        }

        return new Info(withCurMaxValue, maxValue);
    }

    public static class Info{
        int curHeadMaxValue;// 以当前为头的最大值
        int maxValue;// 最大值

        public Info(int curHeadMaxValue, int maxValue) {
            this.curHeadMaxValue = curHeadMaxValue;
            this.maxValue = maxValue;
        }
    }

    public static class Info2{
        int curHeadMaxValue;// 以当前为头的最大值
        int maxValue;// 最大值
        TreeNode maxStartNode;
        TreeNode maxEndNode;
        TreeNode maxHeadEndNode;// 以当前节点为头，获得到最大值的尾节点是哪个

        public Info2(int curHeadMaxValue, int maxValue, TreeNode maxStartNode, TreeNode maxEndNode, TreeNode maxHeadEndNode) {
            this.curHeadMaxValue = curHeadMaxValue;
            this.maxValue = maxValue;
            this.maxStartNode = maxStartNode;
            this.maxEndNode = maxEndNode;
            this.maxHeadEndNode = maxHeadEndNode;
        }
    }

    public static List<TreeNode> getMaxSumPath(TreeNode head) {
        if (head == null) {
            return new ArrayList<>();
        }
        // 获取最大的路径
        Info2 info2 = process2(head);
        Map<TreeNode, TreeNode> parentNodeMap = new HashMap<>();
        initParentMap(head, parentNodeMap);
        List<TreeNode> list = initPath(info2.maxStartNode, info2.maxEndNode, parentNodeMap);

        return list;
    }

    private static List<TreeNode> initPath(TreeNode startNode, TreeNode endNode, Map<TreeNode, TreeNode> map) {
        LinkedList<TreeNode> list = new LinkedList<>();

        TreeNode commonParentNode = getCommonParentNode(startNode, endNode, map);
        int size = 0;
        while (startNode != commonParentNode) {
            list.addLast(startNode);
            startNode = map.get(startNode);
            size++;
        }
        list.add(size++, commonParentNode);
        while (endNode != commonParentNode) {
            list.add(size, endNode);
            endNode = map.get(endNode);
        }
        return list;
    }

    private static TreeNode getCommonParentNode(TreeNode startNode, TreeNode endNode, Map<TreeNode, TreeNode> map) {
        if (startNode == endNode) {
            return startNode;
        }
        List<TreeNode> list = new ArrayList<>();
        while (startNode != null) {
            list.add(startNode);
            startNode = map.get(startNode);
        }
        TreeNode ans = endNode;
        while (!list.contains(endNode)) {
            endNode = map.get(endNode);
            ans = endNode;
        }
        return ans;
    }


    private static void initParentMap(TreeNode head, Map<TreeNode, TreeNode> map) {
        if (head == null) {
            return;
        }
        if (head.right != null) {
            map.put(head.right, head);
        }
        if (head.left != null) {
            map.put(head.left, head);
        }
        initParentMap(head.left, map);
        initParentMap(head.right, map);
    }

    private static Info2 process2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info2 left = process2(root.left);
        Info2 right = process2(root.right);

        int withCurMaxValue = root.val;
        int maxValue = root.val;
        TreeNode maxStartNode = root;
        TreeNode maxEndNode = root;
        TreeNode maxHeadEndNode = root;
        // 分析情况
        // 包含当前节点
        if (left != null) {
            if (left.curHeadMaxValue + root.val > withCurMaxValue) {
                withCurMaxValue = left.curHeadMaxValue + root.val;
                maxHeadEndNode = left.maxHeadEndNode;
                if (withCurMaxValue > maxValue) {
                    maxValue = Math.max(maxValue, withCurMaxValue);
                    maxStartNode = root;
                    maxEndNode = left.maxHeadEndNode;
                }
            }
        }
        if (right != null) {
            if (right.curHeadMaxValue + root.val > withCurMaxValue) {
                withCurMaxValue = right.curHeadMaxValue + root.val;
                maxHeadEndNode = right.maxHeadEndNode;
                if (withCurMaxValue > maxValue) {
                    maxValue = Math.max(maxValue, withCurMaxValue);
                    maxStartNode = root;
                    maxEndNode = right.maxHeadEndNode;
                }
            }
        }
        // 分析这个情况
        if (left != null) {
            if (left.maxValue > maxValue) {
                maxValue = left.maxValue;
                maxStartNode = left.maxStartNode;
                maxEndNode = left.maxEndNode;
            }
        }
        if (right != null) {
            if (right.maxValue > maxValue) {
                maxValue = right.maxValue;
                maxStartNode = right.maxStartNode;
                maxEndNode = right.maxEndNode;
            }
        }
        if (left != null && right != null) {
            if (left.curHeadMaxValue + root.val + right.curHeadMaxValue > maxValue) {
                maxValue = left.curHeadMaxValue + root.val + right.curHeadMaxValue;
                maxStartNode = left.maxHeadEndNode;
                maxEndNode = right.maxHeadEndNode;
            }
        }
        return new Info2(withCurMaxValue, maxValue, maxStartNode, maxEndNode, maxHeadEndNode);
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(-10);
        head.left = new TreeNode(-100);
        head.right = new TreeNode(200);
        head.left.left = new TreeNode(3);
        head.left.right = new TreeNode(9);
        head.right.left = new TreeNode(-6);
        head.right.right = new TreeNode(-3);

        List<TreeNode> maxPath1 = getMaxSumPath(head);
        List<TreeNode> maxPath2 = Problem_0124_BinaryTreeMaximumPathSum.getMaxSumPath(head);

        for (TreeNode n : maxPath1) {
            System.out.println(n.val);
        }
        System.out.println();
        for (TreeNode n : maxPath2) {
            System.out.println(n.val);
        }
    }

}
