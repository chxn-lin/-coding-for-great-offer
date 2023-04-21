package a.com.linshunc.class30;

import class30.Problem_0116_PopulatingNextRightPointersInEachNode;

public class Prob_0116_PopulatingNextRightNode {

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
        public Node() {
        }
    }

    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }
        MyQueue queue = new MyQueue(root);
        int size = queue.size;
        while (size > 0) {
            Node pre = null;
            Node cur = null;
            for (int i = 0; i < size; i++) {
                cur = queue.poll();
                if (pre != null) {
                    pre.next = cur;
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }

                pre = cur;
            }
            pre.next = null;
            size = queue.size;
        }
        return root;
    }

    public static class MyQueue{
        int size;
        Node head;
        Node tail;
        public MyQueue(Node head) {
            this.size = 1;
            this.head = head;
            this.tail = head;
        }

        public MyQueue() {
            this.size = 0;
        }

        public void offer(Node node){
            size++;
            if (head == null) {
                head = node;
                tail = node;
            }
            else {
                tail.next = node;
                tail = node;
            }
        }
        public Node poll(){
            Node res = head;
            head = head.next;
            size--;
            res.next = null;
            return res;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node9 = new Node(9);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        node5.right = node9;

        Node connect = connect(node1);
        System.out.println(connect);
    }

}
