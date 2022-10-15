package a.com.linshunc.class08;

// 本题测试链接 : https://leetcode.com/problems/container-with-most-water/
public class Code02_Find2Line {

    public static int maxArea1(int[] h) {
        int L = 0;
        int R = h.length - 1;
        int maxValue = 0;
        while (L < R) {
            maxValue = Math.max(maxValue, Math.min(h[L], h[R]) * (R - L));
            if (h[L] < h[R]) {
                L++;
            } else {
                R--;
            }
        }
        return maxValue;
    }

}
