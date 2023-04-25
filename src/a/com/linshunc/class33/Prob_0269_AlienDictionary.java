package a.com.linshunc.class33;

import class33.Problem_0269_AlienDictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Prob_0269_AlienDictionary {

    public static void main(String[] args) {
        String[] words = {
                "ddbdbs",
                "dabdbs",
                "cg",
        };
        System.out.println(alienOrder(words));
        System.out.println(Problem_0269_AlienDictionary.alienOrder(words));
    }

    public static String alienOrder(String[] words) {
        StringBuilder str = new StringBuilder();
        if (words.length < 1) {
            return str.toString();
        }
        Map<Character, MyClass> map = new HashMap<>();

        String pre = words[0];
        int type = 0;
        for (int i = 1; i < words.length; i++) {
            char[] fromTo = getFirstDiffChar(pre, words[i]);
            pre = words[i];
            if (fromTo != null) {
                char from = fromTo[0];
                char to = fromTo[1];
                if (!map.containsKey(from)) {
                    type++;
                    map.put(from, new MyClass(from));
                }
                if (!map.containsKey(to)) {
                    type++;
                    map.put(to, new MyClass(to));
                }
                MyClass f = map.get(from);
                MyClass t = map.get(to);
                f.next.add(t);
                t.in++;
            }
        }
        Queue<MyClass> queue = new LinkedList<>();
        for (MyClass value : map.values()) {
            if (value.in == 0) {
                queue.add(value);
            }
        }
        while (!queue.isEmpty()) {
            MyClass poll = queue.poll();
            str.append(poll.c);
            for (MyClass myClass : poll.next) {
                if (--myClass.in == 0) {
                    queue.add(myClass);
                }
            }
        }
        return type != str.length() ? "" : str.toString();
    }

    private static char[] getFirstDiffChar(String pre, String word) {
        char[] arr1 = pre.toCharArray();
        char[] arr2 = word.toCharArray();
        for (int i = 0; i < Math.min(arr1.length, arr2.length); i++) {
            if (arr1[i] != arr2[i]) {
                return new char[]{arr1[i], arr2[i]};
            }
        }
        return null;
    }

    public static class MyClass{
        int in;
        char c;
        List<MyClass> next;

        public MyClass(char c) {
            this.c = c;
            next = new ArrayList<>();
            in = 0;
        }
    }

}
