package a.com.linshunc.class27;

import java.util.Arrays;

import static class27.Code01_PickBands.randomPrograms;
import static class27.Code01_PickBands.right;

public class Code01_PickBands {

    // 暴力解
    public static int minCost(int[][] programs, int nums) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        int useIndex = initArr(programs);// 不同组合下，最优惠的集合，按照 arr[i][0] 升序排列
        int process = process(programs, useIndex, (1 << (nums * 2)) - 1,0, nums, 0, 0);
        return process == Integer.MAX_VALUE ? -1 : process;
    }

    // 分治
    public static int minCost2(int[][] programs, int nums) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        int useIndex = initArr(programs);// 不同组合下，最优惠的集合，按照 arr[i][0] 升序排列

        int[] map = init(1 << (nums << 1));
        int[] map2 = null;
        process2(programs, useIndex, 0, nums >> 1, 0, 0, map);
        if (nums % 2 == 0) {
            map2 = map;
        }
        else {
            map2 = init(1 << (nums << 1));
            process2(programs, useIndex, 0, nums - (nums >> 1), 0, 0, map2);
        }
        // 一次次对比处理
        int allOne = (1 << (nums << 1)) - 1;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < map.length; i++) {
            if (map[i] != Integer.MAX_VALUE && map2[allOne ^ i] != Integer.MAX_VALUE ) {
                res = Math.min(res, map[i] + map2[allOne ^ i]);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private static void process2(int[][] arr, int maxIndex, int index, int rest, int chooseGroup, int cost, int[] map) {
        if (rest == 0) {
            map[chooseGroup] = Math.min(cost, map[chooseGroup]);
        }
        else {
            if (index <= maxIndex) {
                process2(arr, maxIndex, index + 1, rest, chooseGroup, cost, map);
                int[] cur = arr[index];
                if (((1 << cur[0] | 1 << cur[1]) & chooseGroup) == 0) {
                    process2(arr, maxIndex, index + 1, rest - 1, chooseGroup | (1 << cur[0] | 1 << cur[1]), cost + cur[2], map);
                }
            }
        }
    }

    private static int[] init(int size){
        int[] map = new int[size];
        for (int i = 0; i < size; i++) {
            map[i] = Integer.MAX_VALUE;
        }
        return map;
    }

    private static int process(int[][] arr, int maxIndex, int lastChoose, int index, int rest, int chooseGroup, int cost) {
        if (index == maxIndex + 1 || rest < 0) {
            if (rest == 0) {
                if (chooseGroup != lastChoose) {
                    return Integer.MAX_VALUE;
                }
                return cost;
            }
            else {
                return Integer.MAX_VALUE;
            }
        }
        int p1 = process(arr, maxIndex, lastChoose, index + 1, rest, chooseGroup, cost);
        int p2 = Integer.MAX_VALUE;
        int[] ints = arr[index];
        if ((1 << ints[0] & chooseGroup) != 1 && (1 << ints[1] & chooseGroup) != 1) {
            p2 = process(arr, maxIndex, lastChoose, index + 1, rest - 1, chooseGroup | (1 << ints[0]) | (1 << ints[1]), cost + ints[2]);
        }
        return Math.min(p1, p2);
    }

    private static int initArr(int[][] programs) {
        for (int[] program : programs) {
            int x = program[0];
            int y = program[1];
            program[0] = Math.min(x, y);
            program[1] = Math.max(x, y);
        }
        Arrays.sort(programs, (a, b) -> a[0] == b[0] ? (a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]) : a[0] - b[0]);

        int x = programs[0][0];
        int y = programs[0][1];
        int usefulIndex = 0;
        for (int i = 1; i < programs.length; i++) {
            int[] program = programs[i];
            if (program[0] == x && y == program[1]) {
                continue;
            }
            else {
                usefulIndex++;
                swap(programs, i, usefulIndex);
                x = program[0];
                y = program[1];
            }
        }
//        System.out.println("============排序完");
//        for (int[] program : programs) {
//            System.out.println(Arrays.toString(program));
//        }
//        System.out.println(usefulIndex);
        return usefulIndex;
    }

    // 换位
    private static void swap(int[][] programs, int index1, int index2){
        int[] temp = programs[index1];
        programs[index1] = programs[index2];
        programs[index2] = temp;
    }

    // 为了测试
    public static void main(String[] args) {
//        int[][] programs = {
//                {0, 1, 47},
//                {0, 3, 68},
//                {1, 2, 80},
//                {1, 3, 27},
//                {2, 3, 25},
//        };
//        System.out.println(minCost(programs, 2));
//        System.out.println(right(programs, 2));

        int N = 4;
        int V = 100;
        int T = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < T; i++) {
            int nums = (int) (Math.random() * N) + 1;
            int[][] programs = randomPrograms(nums, V);
            int[][] programs2 = copyArr(programs);

//            System.out.println();
//            for (int[] program : programs) {
//                System.out.println(Arrays.toString(program));
//            }
//            System.out.println();

            int ans1 = right(programs, nums);
            int ans2 = minCost2(programs2, nums);
            if (ans1 != ans2) {
                for (int[] program : programs) {
                    System.out.println(Arrays.toString(program));
                }
                System.out.println(nums);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");

        long start;
        long end;
        int[][] programs;

        programs = randomPrograms(7, V);
        start = System.currentTimeMillis();
        right(programs, 7);
        end = System.currentTimeMillis();
        System.out.println("right方法，在nums=7时候的运行时间(毫秒) : " + (end - start));

        programs = randomPrograms(10, V);
        start = System.currentTimeMillis();
        minCost2(programs, 10);
        end = System.currentTimeMillis();
        System.out.println("minCost方法，在nums=10时候的运行时间(毫秒) : " + (end - start));

    }

    private static int[][] copyArr(int[][] arr){
        int[][] newArr = new int[arr.length][3];
        for (int i = 0; i < arr.length; i++) {
            for (int i1 = 0; i1 < arr[i].length; i1++) {
                newArr[i][i1] = arr[i][i1];
            }
        }
        return newArr;
    }

}
