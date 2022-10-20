package a.com.linshunc.class09;

public class Code05_CheckStepSum {

    public static boolean isStepSum(int num) {
        for (int i = 0; ; i++) {
            int s = getStepSum(i);
            if (s == num) {
                return true;
            } else if (s > num) {
                break;
            }
        }
        return false;
    }

    // 二分法
    public static boolean isStepSumFast(int num) {
        int min = 0;
        int max = num;
        int mid = (max + min) >> 1;
        while (max >= min) {
            int cur = getStepSum(mid);
            if (cur == num) {
                return true;
            }
            else if (cur > num) {
                max = mid - 1;
            }
            else {
                min = mid + 1;
            }
            mid = (max + min) >> 1;
        }
        return false;
    }

    private static int getStepSum(int i) {
        int res = 0;
        while (i > 0) {
            res += i;
            i = i / 10;
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 15000; i++) {
            boolean stepSum = isStepSum(i);
            boolean stepSum2 = isStepSumFast(i);
            if (stepSum2 != stepSum) {
                System.out.println("oops");
                System.out.println(i);
                System.out.println(stepSum);
                System.out.println(stepSum2);
                break;
            }
        }
    }

}
