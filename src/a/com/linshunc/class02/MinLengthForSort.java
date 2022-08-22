package a.com.linshunc.class02;

import class02.Code06_MinLengthForSort;

public class MinLengthForSort {

    public static int method1(int[] arr){
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int minLen = arr.length;

        int lastMax = arr[0];
        int moreRightX = -1;
        for (int i = 1; i < arr.length; i++) {
            if (lastMax > arr[i]) {
                moreRightX = i;
            } else {
                lastMax = arr[i];
            }
        }

        int lastMin = arr[arr.length - 1];
        int moreLeftX = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (lastMin < arr[i]) {
                moreLeftX = i;
            } else {
                lastMin = arr[i];
            }
        }
        if (moreRightX == -1) {
            return 0;
        }
        minLen = moreRightX - moreLeftX + 1;

        return minLen;
    }

    public static void main(String[] args) {
        int[] arr = {1,5,13,6,3,9,4,46,41,53};
        System.out.println(method1(arr));
        System.out.println(Code06_MinLengthForSort.findUnsortedSubarray(arr));
    }

}
