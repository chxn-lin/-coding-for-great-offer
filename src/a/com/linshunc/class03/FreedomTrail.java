package a.com.linshunc.class03;

import class03.Code07_FreedomTrail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreedomTrail {

    public static int findRotateSteps(String r, String k) {
        int res = 0;
        char[] arr = r.toCharArray();
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.get(arr[i]).add(i);
            } else {
                List list = new ArrayList();
                list.add(i);
                map.put(arr[i], list);
            }
        }

        return process(map, k.toCharArray(), arr, 0, 0);
    }

    private static int process(Map<Character, List<Integer>> map, char[] aimArr, char[] ringArr, int index, int preIndex) {
        if (index == aimArr.length) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (Integer cur : map.get(aimArr[index])) {
            res = Math.min(getDistance(preIndex, cur, ringArr.length) + 1 + process(map, aimArr, ringArr, index + 1, cur), res);
        }
        return res;
    }

    private static int getDistance(int preIndex, int cur, int ringLen) {
        int dis = Math.min(Math.abs(cur - preIndex), Math.min(ringLen + cur - preIndex, ringLen + preIndex - cur));
        return dis;
    }

    public static void main(String[] args) {
        String ring = "asdjhashkjafsdklhsad";
        String k = "jkasjfhsadk";
        System.out.println(Code07_FreedomTrail.findRotateSteps(ring, k));
        System.out.println(findRotateSteps(ring, k));

//        System.out.println(getDistance(8, 4, 11));
//        System.out.println(Code07_FreedomTrail.dial(8, 4, 11));
    }

}
