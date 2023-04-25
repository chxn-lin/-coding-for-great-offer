package a.com.linshunc.class33;

public class Prob_0277_FindTheCelebrity {

    public static void main(String[] args) {
        System.out.println(new Prob_0277_FindTheCelebrity().findCelebrity(8));
    }

    // 提交时不要提交这个函数，因为默认系统会给你这个函数
    // knows方法，自己认识自己
    public static boolean knows(int x, int i) {
        if (i == 4) {
            // 第4个是明星
            return true;
        }
        if (x == 4) {
            return false;
        }
        return Math.random() < 0.5 ? true : false;
    }

    // 只提交下面的方法 0 ~ n-1
    public int findCelebrity(int n) {
        int cond = 0;
        for (int i = 1; i < n; i++) {
            if (knows(cond, i)) {
                cond = i;
            }
        }
        for (int i = 0; i < cond; i++) {
            if (knows(cond, i)) {
                return -1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (!knows(i, cond)) {
                return -1;
            }
        }

        return cond;
    }

}
