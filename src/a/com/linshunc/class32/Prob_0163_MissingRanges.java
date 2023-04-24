package a.com.linshunc.class32;

import class32.Problem_0163_MissingRanges;

import java.util.ArrayList;
import java.util.List;

public class Prob_0163_MissingRanges {

    public static void main(String[] args) {
        int[] nums = {-1};
        int lower = -2;
        int upper = -1;
        System.out.println(findMissingRanges(nums, lower, upper));
        System.out.println(Problem_0163_MissingRanges.findMissingRanges(nums, lower, upper));
        System.out.println();
    }

    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();

        if (lower > upper) {
            return list;
        }

        int index = 0;
        while (index < nums.length && lower <= upper) {
            if (lower < nums[index]) {
                list.add(getMiss(nums[index], lower));
                lower = nums[index] + 1;
            }
            else if (lower == nums[index]) {
                lower = lower + 1;
            }
            index++;
        }
        if (lower <= upper) {
            list.add(getMiss(upper + 1, lower));
        }

        return list;
    }

    private static String getMiss(int num, int lower) {
        int startNum = lower;
        int endNum = num - 1;
        if (endNum + 1 == startNum) {
            endNum = lower;
        }

        StringBuilder str = new StringBuilder();
        if (startNum == endNum) {
            str.append(startNum);
        }
        else {
            str.append(startNum)
                .append("->")
                .append(endNum);
        }
        return str.toString();
    }

}
