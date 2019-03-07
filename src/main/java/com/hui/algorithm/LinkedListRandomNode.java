package com.hui.algorithm;

import java.util.Random;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/24 23:49
 */
public class LinkedListRandomNode {
    private ListNode HEAD;
    private Random random;

        /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
        public LinkedListRandomNode(ListNode head) {
            this.HEAD = head;
        }


        // https://leetcode.com/problems/linked-list-random-node/discuss/85662/java-solution-with-cases-explain 蓄水池算法  (1/n-1)*(n-1/n)=1/n
    //  https://gregable.com/2007/10/reservoir-sampling.html
        /** Returns a random node's value. */
        public int getRandom() {
            ListNode current = HEAD;
            ListNode r = current;
            random = new Random();
            int i = 1;
            while (current.next != null)
            {
                current = current.next;
                if(random.nextInt(i + 1) == i)
                {
                    r = current;
                }
                i++;

            }

            return r.val;
        }


/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */


//Definition for singly-linked list.
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
}
