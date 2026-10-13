/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int countA=0;
        int countB=0;
        ListNode tempA=headA;
        ListNode tempB=headB;

        while(tempA!=null){
            countA++;
            tempA=tempA.next;

        }
        while(tempB!=null){
            countB++;
            tempB=tempB.next;

        }
        tempA=headA;
        tempB=headB;
        int diff=Math.abs(countA-countB);

        if(countB>countA){
            while(diff-- >0){
                tempB=tempB.next;
            }
        }
        else{
            while(diff-- >0){
                tempA=tempA.next;
            }
        }
        while(tempA!=tempB){
            tempA=tempA.next;
            tempB=tempB.next;

        }
        // System.out.println(countA);
        // System.out.println(countB);
        // System.out.println(diff);

        return tempA;
   
    }
}