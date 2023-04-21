package a.com.linshunc.class31;

import class31.Problem_0150_EvaluateReversePolishNotation;

import java.util.Stack;

public class Prob_0150_EvaluatePolishNotation {

    public static void main(String[] args) {
        String[] strs = {"2", "2", "*", "7", "5", "/", "3", "-", "+"};
        System.out.println(evalRPN(strs));
        System.out.println(Problem_0150_EvaluateReversePolishNotation.evalRPN(strs));
    }

    public static int evalRPN(String[] tokens) {
        String tmp = "";
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (checkIsMath(tokens[i])) {
                tmp = math(stack.pop(), stack.pop(), tokens[i]);
            }
            else {
                tmp = tokens[i];
            }
            stack.add(tmp);
        }
        return Integer.valueOf(stack.pop());
    }

    private static String math(String num1, String num2, String token) {
        String res = "";
        switch (token) {
            case "+":
                res = String.valueOf(Integer.valueOf(num2) + Integer.valueOf(num1));
                break;
            case "-":
                res = String.valueOf(Integer.valueOf(num2) - Integer.valueOf(num1));
                break;
            case "*":
                res = String.valueOf(Integer.valueOf(num2) * Integer.valueOf(num1));
                break;
            case "/":
                res = String.valueOf(Integer.valueOf(num2) / Integer.valueOf(num1));
                break;
        }
        return res;
    }

    private static boolean checkIsMath(String s){
        boolean res = false;
        switch (s) {
            case "+":
            case "-":
            case "*":
            case "/":
                res = true;
                break;
        }
        return res;
    }

}
