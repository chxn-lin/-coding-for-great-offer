package a.com.linshunc.class07;

import class07.Code03_MaxGap;

public class MaxGap {

    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        int length = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            max = Math.max(nums[i], max);
            min = Math.min(nums[i], min);
        }
        boolean[] hasNum = new boolean[length + 1];
        int[] mins = new int[length + 1];
        int[] maxs = new int[length + 1];
        for (int i = 0; i < length; i++) {
            int index = getBucket(nums[i], length, min, max);
            mins[index] = hasNum[index] ? Math.min(mins[index], nums[i]) : nums[i];
            maxs[index] = hasNum[index] ? Math.max(maxs[index], nums[i]) : nums[i];
            hasNum[index] = true;
        }
        int res = 0;
        int lastMax = maxs[0];
        for (int i = 1; i < hasNum.length; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    public static int getBucket(long num, long len, long min, long max){
        return (int) ((num - min) * len / ((max - min)));
    }

    public static void main(String[] args) {
        int[] arr = {1,1,1,1,1,5,5,5,5,5};
        System.out.println(maximumGap(arr));
        System.out.println(Code03_MaxGap.maximumGap(arr));
    }

}
