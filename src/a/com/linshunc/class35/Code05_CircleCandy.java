package a.com.linshunc.class35;

public class Code05_CircleCandy {

    public static void main(String[] args) {
        int[] arr = { 3, 4, 2, 3, 2 };
        System.out.println("true:" + class35.Code05_CircleCandy.minCandy(arr));
        System.out.println(" my :" + minCandy(arr));
    }

    public static int minCandy(int[] arr) {
        if (arr.length < 1) {
            return 0;
        }
        int val = arr[0];
        int minIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < val) {
                minIndex = i;
                val = arr[i];
            }
        }
        int[] newArr = new int[arr.length + 1];
        int index = 0;
        for (int i = minIndex; i < arr.length; i++) {
            newArr[index++] = arr[i];
        }
        for (int i = 0; i <= minIndex; i++) {
            newArr[index++] = arr[i];
        }
        // 去除了两边的影响
        int[] left = new int[newArr.length];
        int[] right = new int[newArr.length];
        left[0] = 1;
        for (int i = 1; i < newArr.length; i++) {
            if (newArr[i] > newArr[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
            else {
                left[i] = 1;
            }
        }
        right[newArr.length - 1] = 1;
        for (int i = newArr.length - 2; i >= 0; i--) {
            if (newArr[i] > newArr[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
            else {
                right[i] = 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < newArr.length - 1; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }

}
