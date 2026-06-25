import java.util.PriorityQueue;

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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

       
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);
        
       
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }
        
        while (!queue.isEmpty()) {
            
            ListNode smallest = queue.poll();
            tail.next = smallest;
            tail = tail.next;
            
            
            if (smallest.next != null) {
                queue.add(smallest.next);
            }
        }
        
        return dummy.next;
    }
}
