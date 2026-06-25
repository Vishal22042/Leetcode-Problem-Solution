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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy, nxt = dummy, prev = dummy;
        
        
        int count = 0;
        while (curr.next != null) {
            curr = curr.next;
            count++;
        }
        
        
        while (count >= k) {
            curr = prev.next; 
            nxt = curr.next;  
            
           
            for (int i = 1; i < k; i++) {
                curr.next = nxt.next;
                nxt.next = prev.next;
                prev.next = nxt;
                nxt = curr.next;
            }
            prev = curr;
            count -= k;
        }
        
        return dummy.next;
    }
}
