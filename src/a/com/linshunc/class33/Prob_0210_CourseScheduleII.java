package a.com.linshunc.class33;

import class33.Problem_0207_CourseSchedule;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Prob_0210_CourseScheduleII {

    public static void main(String[] args) {
        int[][] prerequisites = {
                {1,0},
                {2,0},
                {3,1},
                {3,2},
        };
        int numCourses = 4;
        System.out.println(Arrays.toString(new Prob_0210_CourseScheduleII().findOrder(numCourses, prerequisites)));
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length < 1) {
            return new int[0];
        }
        int[] res = new int[numCourses];
        Map<Integer, Prob_0207_CourseSchedule.MyClass> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int from = prerequisites[i][0];
            int to = prerequisites[i][1];
            if (!map.containsKey(from)) {
                map.put(from, new Prob_0207_CourseSchedule.MyClass(from));
            }
            if (!map.containsKey(to)) {
                map.put(to, new Prob_0207_CourseSchedule.MyClass(to));
            }
            Prob_0207_CourseSchedule.MyClass f = map.get(from);
            Prob_0207_CourseSchedule.MyClass t = map.get(to);
            f.next.add(t);
            t.in++;
        }
        Queue<Prob_0207_CourseSchedule.MyClass> heap = new LinkedList<>();
        for (Prob_0207_CourseSchedule.MyClass value : map.values()) {
            if (value.in == 0) {
                heap.add(value);
            }
        }
        int index = 0;
        while (!heap.isEmpty()) {
            Prob_0207_CourseSchedule.MyClass cur = heap.poll();
            res[index++] = cur.value;
            for (Prob_0207_CourseSchedule.MyClass myClass : cur.next) {
                myClass.in--;
                if (myClass.in == 0) {
                    heap.add(myClass);
                }
            }
        }
        if (index == numCourses) {
            return res;
        }
        else {
            return new int[0];
        }
    }
}
