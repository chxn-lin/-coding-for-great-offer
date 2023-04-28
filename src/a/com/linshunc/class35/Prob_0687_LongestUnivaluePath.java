package a.com.linshunc.class35;

public class Prob_0687_LongestUnivaluePath {

    public static void main(String[] args) {
        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(3);
        head.right = new TreeNode(2);
        head.left.right = new TreeNode(3);
        System.out.println(longestUnivaluePath(head));
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v) {
            val = v;
        }
    }

    private static class Info {
        int curHeadMax;
        int max;
        public Info(int curHeadMax, int max) {
            this.curHeadMax = curHeadMax;
            this.max = max;
        }
    }

    public static int longestUnivaluePath(TreeNode root) {
        return process(root).max;
    }

    private static Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        int curHeadMax = 0;
        int max = 0;
        if (root.left != null && root.left.val == root.val) {
            curHeadMax = Math.max(curHeadMax, left.curHeadMax + 1);
        }
        if (root.right != null && root.right.val == root.val) {
            curHeadMax = Math.max(curHeadMax, right.curHeadMax + 1);
        }
        max = curHeadMax;
        max = Math.max(max, left.max);
        max = Math.max(max, right.max);

        return new Info(curHeadMax, max);
    }

}
