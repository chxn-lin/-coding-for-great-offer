package a.com.linshunc.class04;

import class04.Code01_QueryHobby;

import java.util.*;

import static class04.Code01_QueryHobby.generateRandomArray;

public class FindHowMuchNum {

    private static class FindObj {
        Map<Integer, List<Integer>> map = new HashMap<>();

        public FindObj(int[] arr){
            if (arr != null) {
                for (int i = 0; i < arr.length; i++) {
                    if (map.containsKey(arr[i])) {
                        map.get(arr[i]).add(i);
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        map.put(arr[i], list);
                    }
                }
            }
        }

        public int findNum(int start, int end, int aim){
            List<Integer> list = map.get(aim);
            if (list == null) {
                return 0;
            }
            int a = findLessSize(list, start);
            int b = findLessSize(list, end + 1);

            return b - a;
        }

        private int findLessSize(List<Integer> list, int aim) {
            int mostRightIndex = -1;
            int L = 0;
            int R = list.size() - 1;
            int mid;
            while (L <= R) {
                mid = (L + R) >> 1;
                if (list.get(mid) < aim) {
                    mostRightIndex = mid;
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            return mostRightIndex + 1;
        }

    }

    public static void main(String[] args) {
        int len = 300;
        int value = 20;
        int testTimes = 1000;
        int queryTimes = 1000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(len, value);
            int N = arr.length;
            FindObj box1 = new FindObj(arr);
            Code01_QueryHobby.QueryBox2 box2 = new Code01_QueryHobby.QueryBox2(arr);
            for (int j = 0; j < queryTimes; j++) {
                int a = (int) (Math.random() * N);
                int b = (int) (Math.random() * N);
                int L = Math.min(a, b);
                int R = Math.max(a, b);
                int v = (int) (Math.random() * value) + 1;
                if (box1.findNum(L, R, v) != box2.query(L, R, v)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test end");
    }

}
