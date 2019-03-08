package com.hui.LinkedList;

import java.util.HashMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/27 18:40
 */
public class CopyRandomListNode {

    /**
     *
     * https://leetcode.com/problems/copy-list-with-random-pointer/description/
     * 138. Copy List with Random Pointer
     * DescriptionHintsSubmissionsDiscussSolution
     * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
     *
     * Return a deep copy of the list.
     *
     * @param head
     * @return
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null)return head;

        //copy all the nodes
        RandomListNode current = head;
        HashMap<RandomListNode,RandomListNode> map = new HashMap<>();
        while (current != null)
        {
            map.put(current, new RandomListNode(current.label));
            current = current.next;
        }

        // copy next and random pointers
        current = head;
        while (current != null)
        {
            map.get(current).next = map.get(current.next);
            map.get(current).random = map.get(current.random);
            current = current.next;
        }
        return map.get(head);

    }


    public RandomListNode copyRandomList2(RandomListNode head)
    {
        RandomListNode dummyHead = new RandomListNode(0);
        RandomListNode pre = dummyHead;
        RandomListNode current = head;
        while (current != null)
        {
            pre.next = deepCopy(current);
            current = current.next;
            pre = pre.next;
        }

        return dummyHead.next;
    }

    private RandomListNode deepCopy(RandomListNode randomListNode)
    {
        RandomListNode newnode = new RandomListNode(randomListNode.label);
        if(randomListNode.random != null)
            newnode.random = new RandomListNode(randomListNode.random.label);
        return newnode;
    }

    //Definition for singly-linked list with a random pointer.
    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    };

}
