package a.com.linshunc.class29;

import class29.Problem_0066_PlusOne;

import java.util.Arrays;

public class Prob_0066_PlusOne {

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] = digits[i]+1;
                break;
            }
            else {
                digits[i] = 0;
            }
        }
        if (digits[0] == 0) {
            digits = new int[digits.length + 1];
            digits[0] = 1;
        }
        return digits;
    }

    public static void main(String[] args) {
        int[] digits = {9,9,9,9,9};
        int[] digits2 = Arrays.copyOf(digits, digits.length);

        System.out.println(Arrays.toString(plusOne(digits)));
        System.out.println(Arrays.toString(Problem_0066_PlusOne.plusOne(digits2)));
    }

}
