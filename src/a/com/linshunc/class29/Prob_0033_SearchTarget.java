package a.com.linshunc.class29;

import class29.Problem_0033_SearchInRotatedSortedArray;

public class Prob_0033_SearchTarget {

    public static int search(int[] arr, int num) {
        if (arr.length < 1) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int M = 0;
        int res = -1;
        while (L <= R) {
            M = L + ((R - L) >> 1);
            if (arr[M] == num) {
                return M;
            }
            if (arr[M] == arr[L] && arr[M] == arr[R]) {
                while (L <= M) {
                    if (arr[L] != arr[M]) {
                        break;
                    }
                    L++;
                }
            }
            else if (arr[M] != arr[L]){
                if (arr[M] > arr[L]) {
                    // 断点一定在右边
                    if (arr[M] > num && num >= arr[L]) {
                        R = M - 1;
                    }
                    else {
                        L = M + 1;
                    }
                }
                else if (arr[M] < arr[L]){
                    if (num > arr[M] && num <= arr[R]) {
                        L = M + 1;
                    }
                    else {
                        R = M - 1;
                    }
                }
            }
            else if (arr[M] != arr[R]) {
                if (arr[R] > arr[M]) {
                    // 断点一定在左边
                    if (arr[R] > num && num >= arr[M]) {
                        L = M + 1;
                    }
                    else {
                        R = M - 1;
                    }
                }
                else if (arr[R] < arr[M]){
                    if (num > arr[R] && num <= arr[M]) {
                        R = M - 1;
                    }
                    else {
                        L = M + 1;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2,2,2,2,2,1,2,2,2};
        System.out.println(search(arr, 1));
        System.out.println(Problem_0033_SearchInRotatedSortedArray.search(arr, 1));
    }

}
