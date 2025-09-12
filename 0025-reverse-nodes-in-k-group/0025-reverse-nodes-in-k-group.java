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
    public ListNode reverse(ListNode head){
        if (head == null || head.next == null) return head;
        ListNode newhead = reverse(head.next);
        ListNode front = head.next;
        front.next=head;
        head.next = null;
        return newhead;
    }
    public ListNode getK(ListNode temp,int k){
        k-=1;
        while(temp!=null &&k>0){
            k--;
            temp=temp.next;
        }
        return temp;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        ListNode prev=null;
        while(temp!=null){
            ListNode knode = getK(temp,k);
            if(knode==null){
                if(prev!=null) prev.next=temp;
                break;
            }
            ListNode nextnode = knode.next;
            knode.next=null;
            reverse(temp);
            if(temp==head){
                head=knode;
            }
            else{
                prev.next=knode;
            }
            prev=temp;
            temp=nextnode;
        }
        return head;
    }
}