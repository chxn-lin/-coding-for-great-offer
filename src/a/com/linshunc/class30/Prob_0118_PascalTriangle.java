package a.com.linshunc.class30;

import class30.Problem_0118_PascalTriangle;

import java.util.ArrayList;
import java.util.List;

public class Prob_0118_PascalTriangle {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows < 1) {
            return res;
        }
        List<Integer> firstList = new ArrayList<>();
        firstList.add(1);
        res.add(firstList);

        for (int i = 1; i < numRows; i++) {
            List<Integer> cur = new ArrayList<>();
            cur.add(1);

            List<Integer> lastList = res.get(i - 1);
            for (int j = 1; j < lastList.size(); j++) {
                cur.add(lastList.get(j - 1) + lastList.get(j));
            }
            cur.add(1);

            res.add(cur);
        }
        return res;
    }

    public static void main(String[] args) {
        int k = 10;
        System.out.println(generate(k));
        System.out.println(Problem_0118_PascalTriangle.generate(k));
    }

}
