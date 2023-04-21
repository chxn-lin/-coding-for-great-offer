package a.com.linshunc.class30;

public class Prob_0098_ValidSearchTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class Info{
        boolean isBST;
        int maxValue;
        int minValue;

        public Info(boolean isBST, int maxValue, int minValue) {
            this.isBST = isBST;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isBST;
    }

    private static Info process(TreeNode root){
        if (root == null) {
            return null;
        }
        Info left = process(root.left);
        Info right = process(root.right);

        int max = root.val;
        int min = root.val;
        boolean isBST = true;
        if (left != null) {
            max = Math.max(max, left.maxValue);
            min = Math.min(min, left.minValue);
            isBST = isBST && left.isBST && left.maxValue < root.val;
        }
        if (right != null) {
            max = Math.max(max, right.maxValue);
            min = Math.min(min, right.minValue);
            isBST = isBST && right.isBST && right.minValue > root.val;
        }

        return new Info(isBST, max, min);
    }

    public static void main(String[] args) {


        TreeNode node1 = new TreeNode(1, null, null);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node11 = new TreeNode(11, null, null);

        TreeNode node6 = new TreeNode(6, node4, null);
        TreeNode node15 = new TreeNode(15, node11, null);
        TreeNode node3 = new TreeNode(3, node1, node6);
        TreeNode root = new TreeNode(10, node3, node15);

        System.out.println(isValidBST(root));
    }

}
