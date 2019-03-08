package com.hui.LinkedList;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/15 16:35
 *
 *
 * 单链表 冒泡排序
 */
public class LinkListBubbleSort {

    public void bubbleSort(ListNode head)
    {
        ListNode curr = head;
        ListNode tail = null;
        while (curr != tail)
        {
            boolean noSwap = true;
            while (curr.next != tail)
            {
                if(curr.val > curr.next.val)
                {
                    int temp = curr.val;
                    curr.val = curr.next.val;
                    curr.next.val = temp;
                    noSwap = false;
                }
                curr = curr.next;
            }
            if(noSwap)return;
            tail = curr;
            curr = head;
        }

    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-1);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(4);
        LinkListBubbleSort bubbleSort = new LinkListBubbleSort();
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        bubbleSort.bubbleSort(node1);
        ListNode.printListNode(node1);
    }
}
