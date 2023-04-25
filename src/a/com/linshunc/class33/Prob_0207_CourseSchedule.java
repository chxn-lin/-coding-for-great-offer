package a.com.linshunc.class33;

import class33.Problem_0207_CourseSchedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Prob_0207_CourseSchedule {

    public static void main(String[] args) {
        int[][] prerequisites = {
                {1,0},
                {2,1},
                {1,2},
                {3,2},
                {4,3},
        };
        int numCourses = 5;
        System.out.println(Problem_0207_CourseSchedule.canFinish1(numCourses, prerequisites));
        System.out.println(canFinish1(numCourses, prerequisites));
    }

    public static boolean canFinish1(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length < 1) {
            return true;
        }
        Map<Integer, MyClass> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int from = prerequisites[i][0];
            int to = prerequisites[i][1];
            if (!map.containsKey(from)) {
                map.put(from, new MyClass());
            }
            if (!map.containsKey(to)) {
                map.put(to, new MyClass());
            }
            MyClass f = map.get(from);
            MyClass t = map.get(to);
            f.next.add(t);
            t.in++;
        }
        Queue<MyClass> heap = new LinkedList<>();
        for (MyClass value : map.values()) {
            if (value.in == 0) {
                heap.add(value);
            }
        }
        int count = 0;
        while (!heap.isEmpty()) {
            MyClass cur = heap.poll();
            count++;
            for (MyClass myClass : cur.next) {
                myClass.in--;
                if (myClass.in == 0) {
                    heap.add(myClass);
                }
            }
        }
        return count == numCourses;
    }


    public static class MyClass{
        int in;
        List<MyClass> next;
        int value;

        public MyClass() {
            in = 0;
            value = 0;
            next = new ArrayList<>();
        }
        public MyClass(int val) {
            in = 0;
            value = val;
            next = new ArrayList<>();
        }
    }

}
