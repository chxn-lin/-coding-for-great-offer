package a.com.linshunc.class33;

public class Prob_0237_DeleteNodeInLinkedList {

    public static class ListNode {
        int val;
        ListNode next;
    }

    public void deleteNode(ListNode node) {
        ListNode next = node.next;
        node.val = next.val;
        node.next = next.next;
    }

}
