package a.com.linshunc.class03;

import class03.Code02_HowManyTypes;

import java.util.HashSet;
import java.util.Set;

import static class03.Code02_HowManyTypes.getRandomStringArray;
import static class03.Code02_HowManyTypes.types2;

public class HowManyType {

    public static int method1(String[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();

        for (String s : arr) {
            int type = 0;
            for (char c : s.toCharArray()) {
                type |= (1 << (c - 'a'));
            }
            set.add(type);
        }

        return set.size();
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strMaxSize = 10;
        int arrMaxSize = 100;
        int testTimes = 500000;
        System.out.println("test begin, test time : " + testTimes);
        for (int i = 0; i < testTimes; i++) {
            String[] arr = getRandomStringArray(possibilities, strMaxSize, arrMaxSize);
            int ans1 = method1(arr);
            int ans2 = types2(arr);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");

    }
}
