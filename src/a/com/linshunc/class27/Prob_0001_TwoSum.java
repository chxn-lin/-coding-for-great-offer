package a.com.linshunc.class27;

import class27.Problem_0001_TwoSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Prob_0001_TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return new int[]{-1, -1};
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            else {
                map.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = {1,23,4,10,23,2,6,7,3,13,10};
        int target = 10;
        System.out.println(Arrays.toString(twoSum(nums, target)));
        System.out.println(Arrays.toString(Problem_0001_TwoSum.twoSum(nums, target)));
    }

}
