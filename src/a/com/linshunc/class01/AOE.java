package a.com.linshunc.class01;

import class01.Code06_AOE;

import java.util.Arrays;

import static class01.Code06_AOE.*;

public class AOE {

    public static int method1(int[] x, int[] hp, int range) {
        if (x == null || x.length < 1) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < hp.length; ) {
            if (hp[i] > 0) {
                res += hp[i];
                sub(x, hp, i, x[i] + range, hp[i]);
            }
            i++;
        }
        return res;
    }

    private static void sub(int[] x, int[] hp, int L, int range, int subNum) {
        for (int i = L; ; ) {
            if (i < x.length && x[i] <= range) {
                hp[i] -= subNum;
                i++;
            } else {
                break;
            }
        }
    }

    public static int method2(int[] x, int[] hp, int range) {
        if (x == null || x.length < 1) {
            return 0;
        }
        int res = 0;
        SegmentTree segment = new SegmentTree(hp);
//        Code06_AOE.SegmentTree segment = new Code06_AOE.SegmentTree(hp);
        segment.build(1, hp.length, 1);
        int nextIndex = 1;
        for (int i = 1; i <= hp.length; ) {
            int hpp = segment.query(i, i, 1, hp.length, 1);
            if (hpp > 0) {
                res += hpp;
                for (; nextIndex <= hp.length && x[nextIndex - 1] - x[i - 1] <= range; ) {
                    nextIndex++;
                }
                segment.add(i, nextIndex - 1, -hpp, 1, hp.length, 1);
            }
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] x = {1, 2, 3, 3, 3, 7};
//        int[] hp = {9, 7, 10 ,9, 4, 8};
//        int[] x2 = {1, 2, 3, 3, 3, 7};
//        int[] hp2 = {9, 7, 10 ,9, 4, 8};
//        int range = 4;
//        System.out.println(minAoe3(x, hp, range));
//        System.out.println(method2(x2, hp2, range));

        int N = 10;
        int X = 20;
        int H = 10;
        int R = 5;
        int testTime = 50000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[] x2 = randomArray(len, X);
            Arrays.sort(x2);
            int[] hp2 = randomArray(len, H);
            int[] x3 = copyArray(x2);
            int[] hp3 = copyArray(hp2);
            int[] x5 = copyArray(x2);
            int[] hp5 = copyArray(hp2);
            int range = (int) (Math.random() * R) + 1;
            int ans2 = method2(x2, hp2, range);
            int ans3 = minAoe3(x3, hp3, range);
            if (ans2 != ans3) {
                printArr(x5);
                printArr(hp5);
                System.out.println(range);

                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");

    }

    public static void printArr(int[] x5) {
        System.out.println();
        for (int i : x5) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static class SegmentTree {
        private int MAX;
        private int[] arr;
        private int[] sum;
        private int[] lazy;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int[] orgin) {
            MAX = orgin.length + 1;
            arr = new int[MAX];
            for (int i = 0; i < orgin.length; i++) {
                arr[i + 1] = orgin[i];
            }
            sum = new int[MAX << 2];
            lazy = new int[MAX << 2];
            change = new int[MAX << 2];
            update = new boolean[MAX << 2];
        }

        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        private void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;
                sum[rt << 1] = ln * change[rt];
                sum[rt << 1 | 1] = rn * change[rt];
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                update[rt] = false;
            }
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (l >= L && r <= R) {
                sum[rt] = (r - l + 1) * C;
                update[rt] = true;
                change[rt] = C;
                lazy[rt] = 0;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public void add(int L, int R, int C, int l, int r, int rt) {
            if (l >= L && r <= R) {
                sum[rt] += (r - l + 1) * C;
                lazy[rt] += C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            int res = 0;
            if (l >= L && r <= R) {
                res = sum[rt];
                return res;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                res += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                res += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return res;
        }

    }

}
