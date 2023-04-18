package a.com.linshunc.class28;

import class28.Problem_0026_RemoveDuplicatesFromSortedArray;

import java.util.Arrays;

public class Prob_0026_Remove2Arr {

    public static void main(String[] args) {
        int times = 100_0000;
        for (int i = 0; i < times; i++) {
            int[] arr = randomArr();
            int[] arr2 = copy(arr);
            int i1 = removeDuplicates(arr);
            int i2 = Problem_0026_RemoveDuplicatesFromSortedArray.removeDuplicates(arr2);
            if (i1 != i2) {
                System.out.println("Oops");
                System.out.println(Arrays.toString(arr));
                System.out.println(i1);
                System.out.println(i2);
                return;
            }
        }
        System.out.println("Success");
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int useful = 0;
        int lastVal = nums[0];
        for (int cur = 1; cur < nums.length; cur++) {
            if (lastVal != nums[cur]) {
                useful++;
                lastVal = nums[cur];
            }
        }
        return useful + 1;
    }

    public static int[] randomArr(){
        int len = 10;
        int maxVal = 100;
        int[] arr = new int[len];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxVal);
        }
        Arrays.sort(arr);
        return arr;
    }

    public static int[] copy(int[] arr){
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

}
