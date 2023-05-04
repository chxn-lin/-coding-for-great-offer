package a.com.linshunc.class34;

import java.util.Arrays;

public class Prob_0287_FindTheDuplicateNumber {

    /**
     * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
     * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
     * 你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
     * Leetcode题目 : https://leetcode.com/problems/find-the-duplicate-number/
     */
    public static int findDuplicate(int[] nums) {
        if (nums.length < 2) {
            return -1;
        }
        int slow = nums[0];
        int fast = nums[nums[0]];
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }


    public static int findDuplicate1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] ^ nums[j]) == 0) {
                    return nums[i];
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,3,4,5,6};
        randomNums(nums);

        System.out.println(Arrays.toString(nums));
        System.out.println(findDuplicate(nums));
    }

    private static void randomNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            sw(nums, i, (int)(Math.random() * nums.length));
        }
    }

    private static void sw(int[] nums, int i, int i1) {
        int temp = nums[i];
        nums[i] = nums[i1];
        nums[i1] = temp;
    }

}
