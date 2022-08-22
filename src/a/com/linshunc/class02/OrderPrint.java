package a.com.linshunc.class02;

import class02.Code03_ReceiveAndPrintOrderLine;

import java.util.HashMap;
import java.util.Map;

public class OrderPrint {

    private static class Node {
        String msg;
        Node next;

        public Node(String msg) {
            this.msg = msg;
        }
    }

    private static class Box {
        Map<Integer, Node> head;
        Map<Integer, Node> tail;
        int cur;

        public Box() {
            this.cur = 1;
            head = new HashMap<>();
            tail = new HashMap<>();
        }

        public void receive(int index, String msg){
            if (index < 1) {
                return;
            }
            Node curNode = new Node(msg);
            head.put(index, curNode);
            tail.put(index, curNode);

            if (tail.containsKey(index + 1)) {
                curNode.next = tail.get(index + 1);
                tail.remove(index);
                head.remove(index + 1);
            }
            if (head.containsKey(index - 1)) {
                head.get(index - 1).next = curNode;
                head.remove(index - 1);
                tail.remove(index);
            }
            if (index == cur) {
                while (curNode != null) {
                    System.out.println(curNode.msg);
                    curNode = curNode.next;
                    cur++;
                }
            }
        }

    }

    public static void main(String[] args) {
        // MessageBox only receive 1~N
        Box box = new Box();
        // 1....
        System.out.println("这是2来到的时候");
        box.receive(2,"B"); // - 2"
        System.out.println("这是1来到的时候");
        box.receive(1,"A"); // 1 2 -> print, trigger is 1
        box.receive(4,"D"); // - 4
        box.receive(5,"E"); // - 4 5
        box.receive(7,"G"); // - 4 5 - 7
        box.receive(8,"H"); // - 4 5 - 7 8
        box.receive(6,"F"); // - 4 5 6 7 8
        box.receive(3,"C"); // 3 4 5 6 7 8 -> print, trigger is 3
        box.receive(9,"I"); // 9 -> print, trigger is 9
        box.receive(10,"J"); // 10 -> print, trigger is 10
        box.receive(12,"L"); // - 12
        box.receive(13,"M"); // - 12 13
        box.receive(11,"K"); // 11 12 13 -> print, trigger is 11

    }


}
