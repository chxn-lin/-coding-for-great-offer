package a.com.linshunc.class28;

import class28.Problem_0034_FindFirstAndLastPositionOfElementInSortedArray;

import java.util.Arrays;

public class Prob_0034_FindFirstAndLastIndexArr {

    public static void main(String[] args) {
        int times = 100_0000;
        for (int i = 0; i < times; i++) {
            int[] arr = randomArr();
            int target = (int) (10 * Math.random());
            int[] res1 = searchRange(arr, target);
            int[] res2 = Problem_0034_FindFirstAndLastPositionOfElementInSortedArray.searchRange(arr, target);
            if (!checkSame(res1, res2)) {
                System.out.println("Oops");
                System.out.println(target);
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(res1));
                System.out.println(Arrays.toString(res2));

                return;
            }
        }
        System.out.println("Success");

//        int[] arr = {1, 4, 6, 7, 7, 7};
//        int target = 7;
//        int[] ints = Problem_0034_FindFirstAndLastPositionOfElementInSortedArray.searchRange(arr, target);
//        System.out.println(Arrays.toString(ints));
    }

    public static int[] searchRange(int[] nums, int target) {
        int first = -1;
        int last = -1;
        if (nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target) {
            return new int[]{first, last};
        }
        int ltTargetIndex = getLtTargetIndex(nums, target, 0, nums.length - 1);
        if (nums[ltTargetIndex + 1] == target) {
            first = ltTargetIndex + 1;
            last = getLtTargetIndex(nums, target + 1, first, nums.length - 1);
        }
        return new int[]{first, last};
    }

    // 获取小于该target的最右数字的下标
    public static int getLtTargetIndex(int[] nums, int target, int start, int end){
        if (nums[start] == target) {
            return start - 1;
        }
        if (nums[end] < target) {
            return end;
        }
        int min;
        while (start != end - 1) {
            min = (end + start) >> 1;
            if (nums[min] >= target) {
                end = min;
            }
            else {
                start = min;
            }
        }
        return start;
    }

    public static int[] randomArr(){
        int len = 20;
        int maxVal = 10;
        int[] arr = new int[(int) (Math.random() * len)];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxVal);
        }
        Arrays.sort(arr);
        return arr;
    }

    public static boolean checkSame(int[] arr1, int[] arr2){
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

}
