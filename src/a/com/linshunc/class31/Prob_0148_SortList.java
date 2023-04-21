package a.com.linshunc.class31;

import class31.Problem_0148_SortList;
import class31.Problem_0148_SortList.ListNode;

public class Prob_0148_SortList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(4);
//        ListNode node4 = new ListNode(6);
//        ListNode node5 = new ListNode(2);

        node1.next = node2;
        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;

//        ListNode listNode = Problem_0148_SortList.sortList1(node1);
        ListNode listNode = sortList1(node1);
        while (listNode != null) {
            System.out.println(listNode.val + " -> ");
            listNode = listNode.next;
        }
        System.out.println(listNode);
    }

    public static ListNode sortList1(ListNode head) {
        if (head == null) {
            return null;
        }
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        int step = 1;
        cur = head;

        ListNode pre = null;
        while (step < len) {
            pre = null;
            for (int k = 0; k < (len + step)/(step + 1); k++) {
                ListNode[] info = sort(step, cur);
                ListNode[] me = merge(info[0], info[1], info[2], info[3]);
                // 返回两个值
                if (head == cur) {
                    head = me[0];
                    pre = me[1];
                } else {
                    pre.next = me[0];
                    pre = me[1];
                }
                cur = info[4];
            }
            cur = head;
            step <<= 1;
        }
        if (pre != null) {
            pre.next = null;
        }

        return head;
    }

    private static ListNode[] merge(ListNode leftStart, ListNode leftEnd, ListNode rightStart, ListNode rightEnd) {
        if (rightStart == null) {
            return new ListNode[]{leftStart, leftEnd};
        }
        ListNode head = null;
        ListNode end = null;
        ListNode cur = null;
        ListNode pre = null;
        while (leftStart != leftEnd.next && rightStart != rightEnd.next) {
            if (leftStart.val <= rightStart.val) {
                cur = leftStart;
                leftStart = leftStart.next;
            }
            else {
                cur = rightStart;
                rightStart = rightStart.next;
            }
            if (pre == null) {
                pre = cur;
                head = cur;
            }
            else {
                pre.next = cur;
                pre = cur;
            }
        }
        // 右边的数据
        if (leftStart != leftEnd.next) {
            while (leftStart != leftEnd.next) {
                cur = leftStart;
                leftStart = leftStart.next;
                pre.next = cur;
                pre = cur;
            }
            end = leftEnd;
        }
        else {
            while (rightStart != rightEnd.next) {
                cur = rightStart;
                rightStart = rightStart.next;
                pre.next = cur;
                pre = cur;
            }
            end = rightEnd;
        }

        return new ListNode[]{head, end};
    }

    private static ListNode[] sort(int step, ListNode firstNode) {
        ListNode firstHead = null;
        ListNode firstEnd = null;
        ListNode secondHead = null;
        ListNode secondEnd = null;
        ListNode next = null;

        ListNode cur = firstNode;
        firstHead = cur;
        firstEnd = cur;
        for (int i = 0; i < step && cur != null; i++) {
            firstEnd = cur;
            cur = cur.next;
        }
        secondHead = cur;
        secondEnd = cur;
        for (int i = 0; i < step && cur != null; i++) {
            secondEnd = cur;
            cur = cur.next;
        }
        next = cur;

        return new ListNode[]{firstHead, firstEnd, secondHead, secondEnd, next};
    }
}
