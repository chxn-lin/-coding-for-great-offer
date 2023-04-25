package a.com.linshunc.class33;

import java.util.Arrays;

public class Prob_0283_MoveZeroes {

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 3, 12};
        moveZeroes(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void moveZeroes(int[] nums) {
        int L = 0;
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                sw(nums, L++, index++);
            }
            else {
                index++;
            }
        }
    }

    private static void sw(int[] nums, int i, int i1) {
        int temp = nums[i1];
        nums[i1] = nums[i];
        nums[i] = temp;
    }

}
