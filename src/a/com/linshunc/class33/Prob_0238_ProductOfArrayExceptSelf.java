package a.com.linshunc.class33;

import class33.Problem_0238_ProductOfArrayExceptSelf;

import java.util.Arrays;

public class Prob_0238_ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        int[] arr = {1,2,5,2,2,3,5};
        System.out.println(Arrays.toString(new Prob_0238_ProductOfArrayExceptSelf().productExceptSelf(arr)));
        System.out.println(Arrays.toString(new Problem_0238_ProductOfArrayExceptSelf().productExceptSelf(arr)));
    }

    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        ans[ans.length - 1] = nums[ans.length - 1];
        for (int i = ans.length - 2; i >= 0; i--) {
            ans[i] = nums[i] * ans[i + 1];
        }
        int preMul = 1;
        for (int i = 0; i < ans.length - 1; i++) {
            ans[i] = preMul * ans[i + 1];
            preMul *= nums[i];
        }
        ans[ans.length - 1] = preMul;

        return ans;
    }

}
