package addTwoNumbers;

/**
 * @author shengfei
 * Leetcode #2, Medium
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode node11 = new ListNode(2);
        ListNode node12 = new ListNode(4);
        ListNode node13 = new ListNode(3);
        node11.next = node12;
        node12.next = node13;

        ListNode node21 = new ListNode(5);
        ListNode node22 = new ListNode(6);
        ListNode node23 = new ListNode(4);
        node21.next = node22;
        node22.next = node23;

        System.out.println(addTwoNumbers(node11, node21));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode tmp = head;
        boolean carryFlag = false;
        while (l1 != null || l2 != null || carryFlag) {
            int value = carryFlag ? 1 : 0;
            if (l1 != null) {
                value = value + l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                value += l2.val;
                l2 = l2.next;
            }
            if (value >= 10) {
                carryFlag = true;
                value = value - 10;
            } else {
                carryFlag = false;
            }
            ListNode listNode = new ListNode(value);
            tmp.next = listNode;
            tmp = listNode;

        }

        return head.next;

    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(val);
            ListNode tmp = next;
            while (tmp != null) {
                stringBuilder.append(">" + tmp.val);
                tmp = tmp.next;
            }
            return stringBuilder.toString();
        }
    }
}