package com.hui.LinkedList;
import java.util.PriorityQueue;

/**
 * @author: shenhaizhilong
 * @date: 2019/2/21 9:03
 */
public class MergeKLists {

    /**
     * merge k single linklists by minHeap,
     * time complexity: O(N*log k)
     * space complexity: additional space cost O(k) , since the minHeap space cost is O(k) .
     * @param lists
     * @return Node
     */
    public Node mergeKLists(Node[] lists) {
        if(lists == null || lists.length == 0)return null;
        if(lists.length == 1)return lists[0];

        // dummy head node, recording the node's head.
        Node dummyHead = new Node(0);
        // curr node pointer
        Node curr = dummyHead;
        PriorityQueue<Node> minHeap = new PriorityQueue<>(lists.length, (Node n1, Node n2) -> n1.val - n2.val);
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                minHeap.offer(lists[i]);
            }
        }

        // every time poll out the smallest node from the minHeap
        while (minHeap.size() > 1) {
            Node top = minHeap.poll();
            curr.next = top;
            curr = curr.next;
            if (top.next != null) {
                minHeap.offer(top.next);
            }
        }

        //when minHeap.size == 1, just need to append the last one to curr.next;
        // optimized method to handle one node is very very long
        if (minHeap.size() == 1)
        {
            curr.next = minHeap.poll();
        }

        return dummyHead.next;
    }




    /**
     * merge two sorted single linked list. Time Complexity: O(N*log k)
     * @param left
     * @param right
     * @param dummyHead it's dummy head, just helped to merge two list.
     * @return Node merge results
     */
   private Node merge(Node left, Node right,Node dummyHead)
   {
       Node curr = dummyHead;
       while (left != null && right != null)
       {
           if(left.val < right.val)
           {
               curr.next = left;
               left = left.next;
           }else {
               curr.next = right;
               right = right.next;
           }
           curr = curr.next;
       }

       if(left == null)
       {
           curr.next = right;
       }

       if(right == null)
       {
           curr.next = left;
       }
       return dummyHead.next;
   }

    /**
     * merge k sorted single linked list
     * @param lists
     * @return
     */
    public Node mergeKListsDivideConquer(Node[] lists)
    {
        if(lists == null || lists.length == 0)return null;
        Node dummyHead = new Node(-1); // dummy head node
        int length = lists.length;
        int gap = 1;  // index gap
        // we need gap > 0 to avoid gap overflow.
        while (gap < length && gap > 0)
        {
            for (int i = 0; i < length - gap; i = i + gap*2) {
                lists[i] = merge(lists[i], lists[i + gap], dummyHead);
                dummyHead.next = null; // reuse dummy Head to avoid re-create dummy head.
            }
            gap = gap << 1;
        }
        return lists[0];
    }


    private static class Node
    {
        private int val;
        private Node next;

        public Node(int val)
        {
            this.val = val;
        }
    }

    public static void print(Node list)
   {
       while (list != null)
       {
           System.out.print(list.val + " -> ");
           list = list.next;
       }
   }




    public static void main(String[] args) {

        Node node1 = new Node(0);
        Node node2 = new Node(1);
        Node node3 = new Node(10);

        node1.next = node2;
        node2.next = node3;

        Node node4 = new Node(2);
        Node node5 = new Node(5);
        Node node6 = new Node(11);
        Node node7 = new Node(12);
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        Node node11 = new Node(0);
        Node node12 = new Node(1);
        Node node13 = new Node(10);
        Node node14 = new Node(12);
        Node node15 = new Node(15);
        Node node16 = new Node(19);
        Node node17 = new Node(29);
        Node node18 = new Node(30);
        Node node19 = new Node(300);
        node11.next = node12;
        node12.next = node13;
        node13.next = node14;
        node14.next = node15;
        node15.next = node16;
        node16.next = node17;
        node17.next = node18;
        node18.next = node19;

        Node[] nodes = {node1, node4, node11};
        MergeKLists mergeKLists = new MergeKLists();
        Node ans = mergeKLists.mergeKListsDivideConquer(nodes);
        print(ans);
    }
}
