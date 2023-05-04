package a.com.linshunc.class34;

public class Prob_0328_OddEvenLinkedList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        node1.next = node2;
        node1.next.next = node3;
        node1.next.next.next = node4;
        node1.next.next.next.next = node5;
        node1.next.next.next.next.next = node6;
        node1.next.next.next.next.next.next = node7;

        ListNode listNode = new Prob_0328_OddEvenLinkedList().oddEvenList(node1);
        System.out.println(listNode);
    }

    // 提交时不要提交这个类
    public static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
        public ListNode() {
        }

        @Override
        public String toString() {
            return val + " -> " + next;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode sNode = head;
        ListNode dNode = head.next;
        ListNode cur = dNode.next;
        ListNode sNodeEnd = sNode;
        ListNode dNodeEnd = dNode;
        if (cur == null) {
            return head;
        }
        int count = 3;
        while (cur != null) {
            // 不为空
            if (count % 2 != 0) {
                // 奇数
                sNodeEnd.next = cur;
                sNodeEnd = sNodeEnd.next;
            }
            else {
                dNodeEnd.next = cur;
                dNodeEnd = dNodeEnd.next;
            }
            cur = cur.next;
            count++;
        }
        sNodeEnd.next = dNode;
        dNodeEnd.next = null;
        return sNode;
    }
}
