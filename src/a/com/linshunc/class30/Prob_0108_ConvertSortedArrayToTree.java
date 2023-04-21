package a.com.linshunc.class30;

public class Prob_0108_ConvertSortedArrayToTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums){
        if (nums == null || nums.length < 1) {
            return null;
        }
        return process(nums, 0, nums.length - 1);
    }

    // 将L -> R 范围上的值，转化为平衡二叉树
    private TreeNode process(int[] nums, int L, int R) {
        if (L == R) {
            return new TreeNode(nums[L]);
        }
        if (L > R) {
            return null;
        }
        int M = L + ((R - L) >> 1);
        TreeNode tree = new TreeNode(nums[M]);
        tree.left = process(nums, L, M - 1);
        tree.right = process(nums, M + 1, R);
        return tree;
    }

}
