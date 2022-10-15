package a.com.linshunc.class08;

import java.util.LinkedList;

// 本题测试链接 : https://leetcode.com/problems/basic-calculator-iii/
public class Code01_ExpressionCompute {

    public static int calculate(String str) {
        return f(str.toCharArray(), 0)[1];
    }

    private static int[] f(char[] arr, int i) {
        int[] res = new int[2];
        LinkedList<String> linkedList = new LinkedList<>();
        int curValue = 0;
        while (i <= arr.length) {
            if (i >= arr.length) {
                del(linkedList, curValue);
                res[0] = i + 1;
                break;
            } else if ('(' == arr[i]) {
                int[] f = f(arr, i + 1);
                i = f[0];
                curValue = curValue * 10 + f[1];
            } else if (')' == arr[i]) {
                res[0] = i + 1;
                del(linkedList, curValue);
                break;
            } else {
                if ('*' == arr[i] || '/' == arr[i] || '+' == arr[i] || '-' == arr[i]) {
                    del(linkedList, curValue);
                    linkedList.add(String.valueOf(arr[i]));
                    curValue = 0;
                } else {
                    curValue = curValue * 10 + (arr[i] - '0');
                }
                i++;
            }
        }
        res[1] = getAmount(linkedList);
        return res;
    }

    // 处理 LinkedList 的 * /
    private static void del(LinkedList<String> linkedList, int num) {
        if (!linkedList.isEmpty()) {
            String top = linkedList.pollLast();
            if ("*".equals(top) || "/".equals(top)) {
                if (!linkedList.isEmpty()) {
                    linkedList.add(String.valueOf("*".equals(top) ?
                            num * Integer.valueOf(linkedList.pollLast())
                            : Integer.valueOf(linkedList.pollLast()) / num));
                }
            } else {
                linkedList.add(top);
                linkedList.add(String.valueOf(num));
            }
        } else {
            linkedList.add(String.valueOf(num));
        }
    }

    // 计算最后的值，只有 + -
    private static int getAmount(LinkedList<String> linkedList) {
        int res = 0;
        boolean isAdd = true;
        while (!linkedList.isEmpty()) {
            String pop = linkedList.pollFirst();
            if ("-".equals(pop)) {
                isAdd = false;
            }
            else if ("+".equals(pop)) {
            }
            else {
                if (isAdd) {
                    res = res + Integer.valueOf(pop);
                } else {
                    res = res - Integer.valueOf(pop);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "48*((70-65)-43)+81";
        System.out.println(class08.Code01_ExpressionCompute.calculate(str));
        System.out.println(calculate(str));
    }

}
