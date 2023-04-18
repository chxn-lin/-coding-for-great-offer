package a.com.linshunc.class28;

import class28.Problem_0019_RemoveNthNodeFromEndofList;
import class28.Problem_0019_RemoveNthNodeFromEndofList.ListNode;

public class Prob_0019_RemoveNNode {

//    public static class ListNode {
//        public int val;
//        public ListNode next;
//    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        int nodeSize = 1;
        ListNode cur = head.next;
        while (cur != null) {
            cur = cur.next;
            nodeSize++;
        }
        n = nodeSize - n + 1;// 第 index 个需要删除
        if (n <= 0) {
            return head;
        }
        if (n == 1) {
            return head.next;
        }
        ListNode lastNode = head;

        for (int i = 1; i < n - 1; i++) {
            lastNode = lastNode.next;
        }
        lastNode.next = lastNode.next.next;

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode();
        head.val = 1;
        ListNode node2 = new ListNode();
        node2.val = 2;
        ListNode node3 = new ListNode();
        node3.val = 3;
        ListNode node4 = new ListNode();
        node4.val = 4;
        ListNode node5 = new ListNode();
        node5.val = 5;
        ListNode node6 = new ListNode();
        node6.val = 6;
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

//        ListNode listNode = removeNthFromEnd(head, 6);
        ListNode listNode = Problem_0019_RemoveNthNodeFromEndofList.removeNthFromEnd(head, 5);
        printNode(listNode);
    }

    public static void printNode(ListNode node){
        while (node != null){
            System.out.print(node.val + "-->");
            node = node.next;
        }
        System.out.println();
    }
}
