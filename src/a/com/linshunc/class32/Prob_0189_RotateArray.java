package a.com.linshunc.class32;

import class32.Problem_0189_RotateArray;

import java.util.Arrays;

public class Prob_0189_RotateArray {

    public void rotate1(int[] nums, int k) {
        int len = nums.length;
        int splitIndex = len - k - 1;
        reserve(nums, 0, splitIndex);
        reserve(nums, splitIndex + 1, len - 1);
        reserve(nums, 0, len - 1);
    }

    private void reserve(int[] nums, int L, int R) {
        int temp;
        while (L < R) {
            temp = nums[L];
            nums[L++] = nums[R];
            nums[R--] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
//        new Problem_0189_RotateArray().rotate1(nums, k);
        new Prob_0189_RotateArray().rotate1(nums, k);
        System.out.println(Arrays.toString(nums));
    }

}
