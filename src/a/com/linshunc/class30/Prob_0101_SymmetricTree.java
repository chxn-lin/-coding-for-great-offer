package a.com.linshunc.class30;

public class Prob_0101_SymmetricTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root.left, root.right);
    }

    private static boolean process(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }

        return left.val == right.val && process(left.left, right.right) && process(left.right, right.left);
    }

    public static void main(String[] args) {


        TreeNode nodeLeft2 = new TreeNode(2, null, null);
        TreeNode noderight2 = new TreeNode(2, new TreeNode(3, null, null), null);
        TreeNode root = new TreeNode(1, nodeLeft2, noderight2);


        System.out.println(isSymmetric(root));
    }

}
