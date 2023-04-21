package a.com.linshunc.class30;

import java.util.Arrays;

public class Prob_0088_MergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // 第几个位置
        int index = nums1.length;
        while (m > 0 && n > 0) {
            if (nums1[m - 1] >= nums2[n - 1]) {
                nums1[--index] = nums1[--m];
            }
            else {
                nums1[--index] = nums2[--n];
            }
        }
        while (n > 0) {
            nums1[--index] = nums2[--n];
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1,1,1,6,7,8,9};
        int[] arr2 = {2,4,5,5,6};
        int[] arrAll = new int[arr1.length + arr2.length];
        for (int i = 0; i < arr1.length; i++) {
            arrAll[i] = arr1[i];
        }

        merge(arrAll, arr1.length, arr2, arr2.length);
        System.out.println(Arrays.toString(arrAll));
    }

}
