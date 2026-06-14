/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    ListNode forwards;

    public int pairSum(ListNode head) {
        ListNode slow = head, fast = head, prev = null;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }

        int max = Integer.MIN_VALUE;
        while(slow != null) {
            max = Math.max(max, prev.val + slow.val);
            prev = prev.next;
            slow = slow.next;
        }

        return max;
    }

    public int helper(ListNode backwards) {
        if(backwards == null) {
            return Integer.MIN_VALUE;
        }
        int returnedMax = helper(backwards.next);
        int currMax = Math.max(returnedMax, backwards.val + forwards.val);
        forwards = forwards.next;
        return currMax;

    }
    public int pairSumRecursive(ListNode head) {
        ListNode backwards = head;
        forwards = head;
        return helper(backwards);
    }
    public int pairSumBrute(ListNode head) {
        List<Integer> bruteList = new ArrayList<>();
        ListNode temp = head;
        while(temp != null) {
            bruteList.add(temp.val);
            temp = temp.next;
        }

        int i = 0, j = bruteList.size() - 1;
        int max = Integer.MIN_VALUE;
        while(i < j) {
            max = Math.max(max, bruteList.get(i) + bruteList.get(j));
            i++;
            j--;
        }

        return max;
    }
}
