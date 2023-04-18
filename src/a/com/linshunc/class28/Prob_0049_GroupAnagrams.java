package a.com.linshunc.class28;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prob_0049_GroupAnagrams {

    public static List<List<String>> groupAnagrams1(String[] strs) {
        Map<Integer, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            int cur = 0;
            for (char aChar : chars) {
                cur = cur | (1 << (aChar - 'a'));
            }
            List<String> list = map.get(cur);
            list = list == null ? new ArrayList<>() : list;
            list.add(strs[i]);
            map.put(cur, list);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams1(strs));
    }

}
