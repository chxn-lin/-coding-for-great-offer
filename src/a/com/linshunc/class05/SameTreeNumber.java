package a.com.linshunc.class05;

import class05.Code02_LeftRightSameTreeNumber.Node;

import static class05.Code02_LeftRightSameTreeNumber.randomBinaryTree;
import static class05.Code02_LeftRightSameTreeNumber.sameNumber2;

public class SameTreeNumber {

    private static final String SPLIT = "#,";

    private static class Info {
        int num;
        String preStr;

        public Info(int num, String preStr) {
            this.num = num;
            this.preStr = preStr;
        }
    }

    public static int mySameNumber1(Node head) {
        if (head == null) {
            return 0;
        }
        Info info = process1(head);
        return info.num;
    }

    public static Info process1(Node head){
        if (head == null) {
            return new Info(0, SPLIT);
        }
        Info left = process1(head.left);
        Info right = process1(head.right);

        int num = left.num + right.num
                + (left.preStr.equals(right.preStr) ? 1 : 0);
        String str = head.value + "," + left.preStr + right.preStr;

        return new Info(num, str);
    }

    public static int mySameNumber2(Node head) {
        if (head == null) {
            return 0;
        }
        Info info = process2(head);
        return info.num;
    }

    public static Info process2(Node head){
        if (head == null) {
            return new Info(0, String.valueOf(SPLIT.hashCode()));
        }
        Info left = process2(head.left);
        Info right = process2(head.right);

        int num = left.num + right.num
                + (left.preStr.equals(right.preStr) ? 1 : 0);
        String str = head.value + "," + left.preStr + right.preStr;

        return new Info(num, String.valueOf(str.hashCode()));
    }

    public static void main(String[] args) {
        int maxLevel = 8;
        int maxValue = 4;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            int ans1 = mySameNumber2(head);
            int ans2 = sameNumber2(head);
            if (ans1 != ans2) {
                System.out.println("出错了！");
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }
        System.out.println("测试结束");

    }

}
