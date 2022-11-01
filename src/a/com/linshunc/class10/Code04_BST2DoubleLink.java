package a.com.linshunc.class10;

import class10.Code04_BSTtoDoubleLinkedList;
import class10.Code04_BSTtoDoubleLinkedList.Node;

public class Code04_BST2DoubleLink {

    // 提交时不要提交这个类
//    public static class Node {
//        public int value;
//        public Node left;
//        public Node right;
//
//        public Node(int data) {
//            this.value = data;
//        }
//    }

    private static class Info {
        Node start;
        Node end;
        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    // 提交下面的代码
    public static Node treeToDoublyList(Node head) {
        return process(head).start;
    }

    private static Info process(Node head) {
        if (head == null) {
            return new Info(null, null);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        Node start = null;
        Node end = null;

        start = left.start != null ? left.start :
                head;
        end = right.end != null ? right.end :
                head;
        head.left = left.end;
        if (left.end != null) {
            left.end.right = head;
        }
        head.right = right.start;
        if (right.start != null) {
            right.start.left = head;
        }

        return new Info(start, end);
    }

    public static void main(String[] args) {
        Node head2 = new Node(4);
        head2.left = new Node(2);
        head2.left.left = new Node(1);
        head2.left.right = new Node(3);
        head2.right = new Node(6);
        head2.right.left = new Node(5);

        Node head3 = new Node(2);
        head3.left = new Node(1);
        head3.right = new Node(3);

//        Node node1 = Code04_BSTtoDoubleLinkedList.treeToDoublyList(head);
//        System.out.println(node1);

        Node node2 = treeToDoublyList(head3);
        System.out.println(node2);
        System.out.println();

    }

}
