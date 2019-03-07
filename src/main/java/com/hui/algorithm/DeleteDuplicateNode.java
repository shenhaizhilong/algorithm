package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/15 0:09
 */
public class DeleteDuplicateNode {

    public static Node deleteDuplicateNode(Node head)
    {
        if(head == null || head.next == null)return head;
        Node next = head.next;
        if(head.val == next.val)
        {
            while (next != null && head.val == next.val)
            {
                next = next.next;
            }
            return deleteDuplicateNode(next);
        }

        head.next = deleteDuplicateNode(next);
        return head;
    }
    private static class Node
    {
        int val;
        Node next;
        public Node(int val)
        {
            this.val = val;
        }
    }

    public static void print(Node head)
    {
        while (head != null)
        {
            System.out.print(head.val);
            if(head.next != null)
            {
                System.out.print(" --->");
            }
            head = head.next;
        }
    }
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(2);
        Node node4 = new Node(3);
        Node node5 = new Node(3);
        Node node6 = new Node(3);
        Node node7 = new Node(0);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        print(node1);
        System.out.println();
        Node curr = deleteDuplicateNode(node1);
        print(curr);
    }

}
