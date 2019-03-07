package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/29 11:06
 *
 * 25. Reverse Nodes in k-Group
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 *
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Note:
 *
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 *
 */
public class ReverseNodesinkGroup {



    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;

        ListNode start = head;
        ListNode end = head;
        ListNode next = null;
        int count = 1;
        while (head != null)
        {
            while (count < k && head != null)
            {
                head = head.next;
                count++;
            }

            if(head == null)
            {
                pre.next = start;
                break;
            }

            end = head;
            next = end.next;
            end = reverse(start, end);
            pre.next = end;
            pre = start;
            head = next;
            count = 1;
            start = head;


        }


        return dummyHead.next;
    }

    private ListNode reverse(ListNode start, ListNode end)
    {
       ListNode curr = start;
       ListNode pre = null;
       ListNode stop = end.next;
       while ( curr != null &&  curr != stop)
       {
           ListNode next = curr.next;
           curr.next = pre;
           pre = curr;
           curr = next;
       }
       return end;
    }

    public static void main(String[] args) {

        ListNode root2 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        root2.next = node2;
        ListNode.insert(root2,3);
        ListNode.insert(root2,4);
        ListNode.insert(root2,5);

        ReverseNodesinkGroup reverseNodesinkGroup = new ReverseNodesinkGroup();
        reverseNodesinkGroup.reverseKGroup(root2, 6);
        ListNode.printListNode(node2);
    }
}
