package a.com.linshunc.class09;

import class09.Code04_EnvelopesProblem;

import java.util.Arrays;
import java.util.Comparator;

public class Code04_EnvelopesPro {

    private static class Mail {
        int w;
        int h;
        public Mail(int w, int h) {
            this.w = w;
            this.h = h;
        }
    }

    // 升序
    private static class MyComp implements Comparator<Mail> {
        @Override
        public int compare(Mail o1, Mail o2) {
            return o1.w == o2.w ? o2.h - o1.h : o1.w - o2.w;
        }
    }

    // 升序
    private static class MyComp2 implements Comparator<Mail> {
        @Override
        public int compare(Mail o1, Mail o2) {
            return o1.w == o2.w ? o2.h - o1.h : o2.w - o1.w;
        }
    }

    public static int method1(int[][] envelopes){
        if (envelopes == null || envelopes.length < 1) {
            return 0;
        }
        Mail[] arr = new Mail[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            arr[i] = new Mail(envelopes[i][0], envelopes[i][1]);
        }
        Arrays.sort(arr, new MyComp2());
        return process(arr, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    private static int process(Mail[] arr, int index, int lastH, int lastW) {
        if (index == arr.length) {
            return 0;
        }
        int p1 = process(arr, index + 1, lastH, lastW);
        int p2 = 0;
        if (arr[index].h < lastH && arr[index].w < lastW) {
            p2 = process(arr, index + 1, arr[index].h, arr[index].w) + 1;
        }
        return Math.max(p1, p2);
    }

    public static int method2(int[][] envelopes){
        if (envelopes == null || envelopes.length < 1) {
            return 0;
        }
        Mail[] arr = new Mail[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            arr[i] = new Mail(envelopes[i][0], envelopes[i][1]);
        }
        Arrays.sort(arr, new MyComp());
        int[] newArr = new int[arr.length];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = arr[i].h;
        }

        int[] end = new int[arr.length];
        int curEndIndex = 0;
        int L = 0;
        int R = 0;
        end[0] = arr[0].h;

        for (int i = 1; i < arr.length; i++) {
            L = 0;
            R = curEndIndex;
            int lastSmallInd = -1;
            if (end[R] < arr[i].h) {
                lastSmallInd = R;
            }
            else if (end[L] > arr[i].h) {
                lastSmallInd = -1;
            }
            else {
                while (L <= R) {
                    int mid = L + ((R - L) >> 1);
                    if (end[mid] >= arr[i].h) {
                        R = mid - 1;
                    } else {
                        L = mid + 1;
                        lastSmallInd = mid;
                    }
                }
            }
            // 最大，那么需要扩充
            if (lastSmallInd == curEndIndex) {
                end[++curEndIndex] = arr[i].h;
            } else {
                end[++lastSmallInd] = arr[i].h;
            }
        }
        return curEndIndex + 1;
    }

    public static int[][] randomArray(int size, int range) {
        int[][] arr = new int[size][2];
        for (int i = 0; i < size; i++) {
            arr[i][0] = (int) (Math.random() * range) + 1;
            arr[i][1] = (int) (Math.random() * range) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
//        int[][] arr = {
//                {5,5},
//                {6,6},
//                {6,5},
//        };
//        int ans1 = Code04_EnvelopesProblem.maxEnvelopes(arr);
//        int ans2 = method1(arr);
//            System.out.println(ans1);
//            System.out.println(ans2);

        int maxSize = 15;
        int range = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * maxSize) + 2;
            int[][] arr = randomArray(size, range);
            int ans1 = Code04_EnvelopesProblem.maxEnvelopes(arr);
            int ans2 = method1(arr);
            if (ans1 != ans2) {
//                printArr(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

}
