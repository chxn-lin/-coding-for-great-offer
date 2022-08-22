package a.com.linshunc.class02;

import class02.Code01_ChooseWork;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

import static a.com.linshunc.class01.AOE.printArr;

public class ChooseWork {

    public static int[] method1(int[] hard, int[] money, int[] ability){
        Job[] arr = new Job[hard.length];
        for (int i = 0; i < hard.length; i++) {
            arr[i] = new Job(hard[i], money[i]);
        }
        Arrays.sort(arr, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.hard - o2.hard != 0 ? o1.hard - o2.hard : o2.money - o1.money;
            }
        });
        int[] res = new int[ability.length];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int lastHard = Integer.MIN_VALUE;
        int lastMoney = 0;
        map.put(lastHard, lastMoney);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].hard != lastHard && arr[i].money > lastMoney) {
                lastHard = arr[i].hard;
                lastMoney = arr[i].money;
                map.put(lastHard, lastMoney);
            }
        }
        for (int i = 0; i < ability.length; i++) {
            res[i] = map.get(map.floorKey(ability[i]));
        }

        return res;
    }

    private static class Job {
        int hard;
        int money;

        public Job(int hard, int money) {
            this.hard = hard;
            this.money = money;
        }
    }

    public static void main(String[] args) {
        int[] hard = {1,3,4,1,6,7,1,4,0};
        int[] money = {2,4,6,8,2,34,2,5,8};
        int[] ability = {0,3,5,7,5,7,8,11,1,4};

        int[] res1 = method1(hard, money, ability);
        int[] res2 = Code01_ChooseWork.getMoneys(hard, money, ability);
        printArr(res1);
        printArr(res2);
    }

}
