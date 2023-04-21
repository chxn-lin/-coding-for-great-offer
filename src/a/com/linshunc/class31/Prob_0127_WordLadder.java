package a.com.linshunc.class31;

import class31.Problem_0127_WordLadder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Prob_0127_WordLadder {

    public static int ladderLength1(String start, String to, List<String> list) {
        if (!list.contains(to)) {
            return 0;
        }
        list.add(start);
        Map<String, List<String>> map = genateMap(list);
        Set<String> set = new HashSet<>();
        int process = process(map, start, to, set);

        return process == Integer.MAX_VALUE ? 0 : process;
    }

    private static int process(Map<String, List<String>> map, String cur, String to, Set<String> set) {
        if (cur.equals(to)) {
            return 1;
        }
        int res = Integer.MAX_VALUE;
        for (String s : map.get(cur)) {
            if (set.contains(s)) {
                continue;
            }
            set.add(s);
            int process = process(map, s, to, set);
            if (process != Integer.MAX_VALUE) {
                res = Math.min(res, process + 1);
            }
            set.remove(s);
        }

        return res;
    }

    private static Map<String, List<String>> genateMap(List<String> list) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : list) {
            List<String> curList = new ArrayList<>();
            map.put(s, curList);

            char[] chars = s.toCharArray();
            // i位置
            for (int i = 0; i < chars.length; i++) {
                for (char j = 'a'; j <= 'z'; j++) {
                    if (j == chars[i]) {
                        continue;
                    }
                    char tmp = chars[i];
                    chars[i] = j;
                    if (list.contains(String.valueOf(chars))) {
                        curList.add(String.valueOf(chars));
                    }
                    chars[i] = tmp;
                }
            }
        }

        return map;
    }

    public static void main(String[] args) {
        String from = "aea";
        String to = "aaa";
        List<String> list1 = new ArrayList(Arrays.asList(
                "aaa",
                "abf",
                "aef",
                "aea",

                "ada",
                "adc"
        ));
        List<String> list2 = new ArrayList<>(list1);

        System.out.println(ladderLength1(from, to, list1));
        System.out.println(Problem_0127_WordLadder.ladderLength1(from, to, list2));
    }

}
