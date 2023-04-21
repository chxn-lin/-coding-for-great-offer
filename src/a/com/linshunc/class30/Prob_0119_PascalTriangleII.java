package a.com.linshunc.class30;

import class30.Problem_0119_PascalTriangleII;

import java.util.ArrayList;
import java.util.List;

public class Prob_0119_PascalTriangleII {

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if (rowIndex < 1) {
            return res;
        }
        res.add(1);
        int lastNum = 0;
        for (int i = 2; i <= rowIndex; i++) {
            lastNum = res.get(0);
            for (int j = 1; j < i - 1; j++) {
                int curNum = res.get(j);
                res.set(j, lastNum + res.get(j));
                lastNum = curNum;
            }
            res.add(1);
        }
        return res;
    }

    public static void main(String[] args) {
        int k = 9;
        System.out.println(getRow(k));
        System.out.println(new Problem_0119_PascalTriangleII().getRow(k - 1));
    }

}
