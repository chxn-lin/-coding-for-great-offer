package a.com.linshunc.class35;

import java.util.HashMap;
import java.util.Map;

public class Prob_0454_4SumII {

    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int val = A[i] * B[j];
                if (countMap.containsKey(val)) {
                    countMap.put(val, countMap.get(val) + 1);
                }
                else {
                    countMap.put(val, 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int val = C[i] * D[j];
                if (countMap.containsKey(-val)) {
                    ans += countMap.get(-val);
                }
            }
        }
        return ans;
    }

}
