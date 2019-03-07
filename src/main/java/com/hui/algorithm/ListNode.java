package com.hui.algorithm;

import java.util.*;

/**
 *
 * https://leetcode-cn.com/submissions/detail/3626304/
 * https://www.cnblogs.com/HorribleMe/p/4878833.html
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * @author: shenhaizhilong
 * @date: 2018/6/29 19:45
 */
public class ListNode {

    public    int val;
    public    ListNode next;

    public ListNode(int x)
    {
        this.val = x;
    }


    public static void insert(ListNode listNode, int  n)
    {
        if(listNode == null)
        {
           throw new NullPointerException("ListNode is null");
        }
        ListNode current = listNode;
        ListNode pre;
        while (current !=null)
        {
            pre = current;
            current = current.next;
            if(current ==null)
            {
                pre.next = new ListNode(n);
            }
        }


    }

    public static void printListNode( ListNode head)
    {
       ListNode current = head;
        while (current !=null)
        {
            System.out.print(current.val + ",");
            current = current.next;
        }
    }

    /**
     * https://leetcode-cn.com/problems/reverse-linked-list/description/
     * 反转一个单链表。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * 进阶:
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     * @param head
     * @return
     */
    public static ListNode reverse( ListNode head)
    {
        if(head == null || head.next == null)return head;
        ListNode prev = null;
        ListNode next = null;
        while (head !=null)
        {
           next = head.next;
           head.next = prev;
           prev = head;
           head = next;
        }
      return prev;

    }

    /**
     * 递归方法反转链表
     * @param head
     * @return
     */
    public static ListNode reverse2(ListNode head)
    {
        if(head == null || head.next == null)return head;

        ListNode newHead = reverse2(head.next); // 先反转后面的链表
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * https://blog.csdn.net/IT_xiaoye/article/details/80461446
     * https://leetcode-cn.com/problems/reverse-linked-list-ii/description/
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     *
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {

        if(head == null || head.next == null)return head;
        if(m < 1 || n <1 || m > n) throw new IllegalArgumentException("Illegal argument");
        if(m == n) return head;
       ListNode current = head;
       ListNode end = current;
       ListNode start = current;
       ListNode pre = null;
       ListNode next = null;

        //move to mth node
       int i = 1;
       for(; i < m; i++)
       {
           start = current;
           current = current.next;
           end = current;
       }

        for (i = 0; i <= n - m; ++i) {
           next = current.next;
           current.next = pre;
           pre = current;
           current = next;
        }
        start.next = pre;
        end.next = current;
        if(m ==1)
            return pre;
        return head;



    }


    public static ListNode findMiddle(ListNode head)
    {
        if(head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast !=null && fast.next !=null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }


    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        ListNode middle = findMiddle(head);
        middle = reverse(middle);

        ListNode p1 = head, p2 = middle;
        while (p1 != null && p2 != null) {
            if(p1.val != p2.val)
                return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;

    }

    /**
     * https://leetcode-cn.com/problems/partition-list/description/
     * 分隔链表
     * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
     *
     * 你应当保留两个分区中每个节点的初始相对位置。
     *
     * 示例:
     *
     * 输入: head = 1->4->3->2->5->2, x = 3
     * 输出: 1->2->2->4->3->5
     *
     * 思路：将链表分割为两个链表，小于x的在前面的链表，大于等于x的放在后面的链表，然后把两个链表链接起来
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition(ListNode head, int x) {

        if(head == null)return head;
        ListNode before  = new ListNode(Integer.MIN_VALUE);
        ListNode after = new ListNode(Integer.MIN_VALUE);
        ListNode beforeHead = before;
        ListNode afterHead= after;
        while (head !=null)
        {

            if(head.val < x)
            {
                before.next = head;
                before = before.next;
            }else if(head.val >=x)
            {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }

        after.next = null;
        before.next = afterHead.next;
        return beforeHead.next;


    }


    /**
     *
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/description/
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     *
     * 示例：
     *
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     *
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     *
     * 给定的 n 保证是有效的。
     *
     * 进阶：
     *
     * 你能尝试使用一趟扫描实现吗？
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {

        if(n<= 0)return head;
        if(head == null)return head;
        ListNode current = head;
        ListNode slow = current;
        ListNode fast = current;
        ListNode pre = current;

        //let fast pointer go n-1 step first.
        while (fast != null && n -1 >0)
        {
            fast = fast.next;
            n--;
        }

        // fast and slow go together.
        while (fast != null && slow !=null && fast.next !=null)
        {
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }
        // if n == length of(listnode)
        if(slow == current)
        {
            return slow.next;
        }

        //remove slow node
        pre.next = slow.next;

        return head;



    }

    //bad method
//    public static boolean hasCycle2(ListNode head) {
//
//        if(head == null || head.next == null) return false;
//        ListNode fast = head.next.next;
//
//        while (fast != head)
//        {
//            if(fast ==null)return false;
//            fast = fast.next;
//
//        }
//        return true;
//    }


    public static boolean hasCycle(ListNode head) {

        if(head == null || head.next == null) return false;
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null)
        {

            fast = fast.next;
            if( fast !=null)
            {
                fast = fast.next;
                slow = slow.next;
            }else {
                return false;
            }
            if(fast == slow)return true;

        }
        return false;
    }

    public static boolean hasCycle2(ListNode head) {

        if (head == null || head.next == null) return false;
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast != slow)
        {
            if(fast.next == null)return false;
            fast = fast.next.next;
            slow = slow.next;
        }
        return fast == slow;
    }

    /**
     *https://stackoverflow.com/questions/3952805/proof-of-detecting-the-start-of-cycle-in-linked-list
     *
     * https://stackoverflow.com/questions/2936213/explain-how-finding-cycle-start-node-in-cycle-linked-list-work
     * https://en.wikipedia.org/wiki/Cycle_detection#Tortoise_and_hare
     *
     * Detecting Loop:
     *
     * Have two pointers, classically called hare and tortoise. Move hare by 2 steps and tortoise by 1. If they meet at some point, then there is surely a cycle and the meeting point is obviously inside the cycle.
     *
     * Finding length of Loop:
     *
     * Keep one pointer fixed at meeting point while increment the other until they are same again. Increment a counter as you go along and the counter value at meet will be the length of cycle.
     *
     * Find the start of cycle
     *
     * Take one pointer to start of the list and keep the other at the meeting point. Now increment both by one and the meet point is the start of loop.
     *
     * head -> 1 -> 2 -> 3 -> 4 -> 5
     *                   ^         |
     *                   |         V
     *                   8 <- 7 <- 6
     *                              \
     *                               AB (where A and B first met).
     *
     * 假设头结点到环入口的距离为a，环入口到快慢指针相遇的结点距离为x，
     * 环的长度为r，慢指针总共走了s步，则快指针走了2s步。
     * 另外，快指针要追上慢指针的话快指针至少要在环里面转了一圈多(假设转了n圈加x的距离)，得到以下关系：
     *     s = a + x;
     *     2s = a + nr + x;
     *     =>a + x = nr;
     *     =>a = nr - x;
     *
     *     若在头结点和相遇结点分别设一指针，同步(单步)前进，则最后一定相遇在环入口结点
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
       ListNode slow = head, fast = head;
       while (fast != null && fast.next != null)
       {
           fast = fast.next.next; //2 hops
           slow = slow.next;      //1 hops
           // // fast caught up to slow, so there is a loop
           if(fast == slow)break;
       }

       // if fast == null or fast.next == null mean's there isn't a cycle.
       if(fast == null || fast.next == null)return null;
       slow = head;
       while (slow != fast)
       {
           slow = slow.next;
           fast = fast.next;
       }

       return fast;


    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if(l1 == null)return l2;
        if(l2 == null)return l1;
        ListNode newHead = null;

        if(l1.val <= l2.val)
        {
            newHead = l1;
            newHead.next = mergeTwoLists(l1.next, l2);
        }else
        {
            newHead = l2;
            newHead.next = mergeTwoLists(l2.next, l1);
        }
        return newHead;

    }


    /**
     *https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/6/linked-list/41/
     *
     * https://blog.csdn.net/sipherhern/article/details/52187783
     * 删除链表中的节点
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
     *
     * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
     *
     *     4 -> 5 -> 1 -> 9
     * 示例 1:
     *
     * 输入: head = [4,5,1,9], node = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * 示例 2:
     *
     * 输入: head = [4,5,1,9], node = 1
     * 输出: [4,5,9]
     * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     * 说明:
     *
     * 链表至少包含两个节点。
     * 链表中所有节点的值都是唯一的。
     * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
     * 不要从你的函数中返回任何结果。
     *
     * @param node
     */
    public static void deleteNode(ListNode node) {
            if(node == null || node.next == null)return;
            ListNode next = node.next;
            node.next = next.next;
            node.val = next.val;
    }


    /**
     *
     *https://leetcode-cn.com/problems/intersection-of-two-linked-lists/description/
     *相交链表
     * 编写一个程序，找到两个单链表相交的起始节点。
     *
     *
     *
     * 例如，下面的两个链表：
     *
     * A:          a1 → a2
     *                    ↘
     *                      c1 → c2 → c3
     *                    ↗
     * B:     b1 → b2 → b3
     * 在节点 c1 开始相交。
     *
     *
     *
     * 注意：
     *
     * 如果两个链表没有交点，返回 null.
     * 在返回结果后，两个链表仍须保持原有的结构。
     * 可假定整个链表结构中没有循环。
     * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;

        int headALength = 0;
        int headBLength = 0;
        ListNode currentA = headA;
        ListNode currentB = headB;

        //find last node
        while (currentA != null)
        {
                headALength++;
                currentA = currentA.next;
        }

        // find last node
        while (currentB != null)
        {
                headBLength++;
               currentB = currentB.next;
        }

        System.out.println("length of A: " +  headALength);
        System.out.println("length of B: " + headBLength);
        if(currentA != currentB)return null;  // not intersection

        int diff = headALength - headBLength;
        System.out.println("diff is: " + diff);
        ListNode longerList = null;
        ListNode shorterList = null;

        if(diff != 0)
        {
            longerList = (diff > 0) ? headA : headB;
            shorterList = (diff <0) ? headA : headB;
            diff = Math.abs(diff);
            while (diff>0)
            {
                System.out.println(" longerList.val: " + longerList.val);
                longerList = longerList.next;
                diff--;
            }
        }else {
            longerList = headA;
            shorterList = headB;
        }


        while (longerList != null && shorterList != null)
        {
            System.out.println(longerList.val);

            if(longerList == shorterList)
            {
                System.out.println("find it: " + longerList.val);
                return longerList;
            }
            longerList = longerList.next;
            shorterList = shorterList.next;
        }

        return null;

    }

    /**
     *
     * https://leetcode-cn.com/problems/rotate-list/description/
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     *
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * 示例 2:
     *
     * 输入: 0->1->2->NULL, k = 4
     * 输出: 2->0->1->NULL
     * 解释:
     * 向右旋转 1 步: 2->0->1->NULL
     * 向右旋转 2 步: 1->2->0->NULL
     * 向右旋转 3 步: 0->1->2->NULL
     * 向右旋转 4 步: 2->0->1->NULL
     *
     * 解法： 1.首尾相接，把单链变成环链
     *       2.len = length(single link list), k = k% len,  head  指针向右移动 len - k
     *       3.把环链变成单链
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k)
    {
        if(head == null || head.next == null || k == 0)return head;
        int len = 0;
        ListNode current = head;
        ListNode pre = null;
        while (current != null)
        {
            // the length of this link list
            len++;
            pre = current;
            current = current.next;
        }

        //change single link list to cycle link list
        pre.next = head;
        //rotate right has cycle
        k = k %len;
        k = len - k;
        while (k > 0)
        {
            pre  = head;
            head = head.next;
            k--;

        }
        // change from cycle link list to single link list.
        pre.next = null;
        return head;


    }


/**
 *https://leetcode.com/problems/remove-linked-list-elements/description/
 *
 * Remove Linked List Elements
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example:
 *
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5

 **/

    public ListNode removeElements(ListNode head, int val) {
        if(head == null)return head;
        ListNode pre = null;
        ListNode current = head;
        while (current != null)
        {
            if(current.val != val)
            {
                pre  = current;
                current = current.next;
            }else {
                if(pre == null)
                {
                    head = head.next;
                    pre = null;
                    current = head;
                }else {
                    pre.next = current.next;
                    current = current.next;
                }
            }
        }
        return head;
    }

    public void print(ListNode head)
    {
        List<Integer> list = new ArrayList<>();
        ListNode current = head;
        while (current != null)
        {
            list.add(current.val);
            current = current.next;
        }

        System.out.println(list);

    }


    /**
     *
     * https://leetcode.com/problems/add-two-numbers/description/
     * Add Two Numbers
     * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     *
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * Example:
     *
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null)return l2;
        if(l2 == null)return l1;

        ListNode up = l1;
        ListNode down = l2;
        ListNode newHead = new ListNode(0);
        ListNode current = newHead;


        int carrier = 0;
        int sum = 0;
        int r = 0;

        while (up != null || down != null)
        {
           int left = (up == null)? 0:up.val;
           int right = (down == null)? 0 : down.val;
           sum = left + right + carrier;
            r = sum % 10;
            carrier = sum /10;
            current.next = new ListNode(r);
            current = current.next;
            if(up != null)
            {
                up = up.next;
            }

            if(down != null)
            {
                down = down.next;
            }

        }

        if(carrier > 0){
            current.next = new ListNode(carrier);
        }



        return newHead.next;
    }


    /**
     *
     *445. Add Two Numbers II
     * DescriptionHintsSubmissionsDiscussSolution
     * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     *
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * Follow up:
     * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
     *
     * Example:
     *
     * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 8 -> 0 -> 7
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        l1 =  reverse(l1);
        l2 =  reverse(l2);
        if(l1 == null)return l2;
        if(l2 == null)return l1;

        ListNode up = l1;
        ListNode down = l2;
        ListNode current = null;
        ListNode temp = null;


        int carrier = 0;
        int sum = 0;
        int r = 0;

        while (up != null || down != null)
        {
            sum = carrier;
            if(up != null)
            {
                sum += up.val;
                up = up.next;
            }
            if(down != null)
            {
                sum += down.val;
                down = down.next;
            }
            r = sum % 10;
            carrier = sum /10;
            temp = new ListNode(r);
            temp.next = current;
            current = temp;

        }

        if(carrier > 0){
            temp = new ListNode(carrier);
            temp.next = current;
            current = temp;
        }



        return current;

    }

    public ListNode addTwoNumbersII2(ListNode l1, ListNode l2) {
        if(l1 == null)return l2;
        if(l2 == null)return l1;
        Deque<Integer> up = new ArrayDeque<>();
        Deque<Integer> down = new ArrayDeque<>();
        while (l1 != null)
        {
            up.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null)
        {
            down.push(l2.val);
            l2 = l2.next;
        }


        ListNode temp = null;
        ListNode current = null;
        int carry = 0;
        int sum = 0;
        while (!up.isEmpty() || !down.isEmpty())
        {
            sum = carry;
            if(!up.isEmpty())
            {
                sum += up.pop();
            }

            if(!down.isEmpty())
            {
                sum += down.pop();
            }

            carry = sum /10;
            temp = new ListNode(sum % 10);
            temp.next = current;
            current = temp;

        }

        if(carry > 0)
        {
            temp = new ListNode(carry);
            temp.next = current;
            current = temp;
        }

        return current;
    }

    /**
     *
     * 147. Insertion Sort List
     * DescriptionHintsSubmissionsDiscussSolution
     * Sort a linked list using insertion sort.
     *
     *
     * A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
     * With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list
     *
     *
     * Algorithm of Insertion Sort:
     *
     * Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
     * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
     * It repeats until no input elements remain.
     *
     * Example 1:
     *
     * Input: 4->2->1->3
     * Output: 1->2->3->4
     * Example 2:
     *
     * Input: -1->5->3->4->0
     * Output: -1->0->3->4->5
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        ListNode current = head;
        ListNode pre = dummyHead;
        while (current != null)
        {
            pre = dummyHead;
            while (pre.next != null && pre.next.val < current.val)
            {
                pre = pre.next;
            }
            ListNode next = current.next;
            current.next = pre.next;
            pre.next = current;
            current = next;

        }
        return dummyHead.next;

    }

    /**
     *
     * 148. Sort List
     * DescriptionHintsSubmissionsDiscussSolution
     * Sort a linked list in O(n log n) time using constant space complexity.
     *
     * Example 1:
     *
     * Input: 4->2->1->3
     * Output: 1->2->3->4
     * Example 2:
     *
     * Input: -1->5->3->4->0
     * Output: -1->0->3->4->5
     *
     * time complexity is O(nlog(n))
     * space complexity is O(log(n)), but if we change the dummy head to class variable, then we didn't create log(n) dummy head
     * so the space complexity can downgrade to O(1)
     * @param head
     * @return
     */
    public ListNode mergeSortList(ListNode head)
    {
        if(head == null || head.next == null)return head;
        ListNode fast = head, slow = head;
        //快慢指针，快指针每次移动两步，慢指针移动一步，目的是找到中间节点，把整个list 划分为两部分
        while (fast.next != null && fast.next.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
        }
        //中间节点 fast
        fast = slow.next;
        slow.next = null;
        //对左半部分排序
        ListNode left = mergeSortList(head);
        //对右半部分排序
        ListNode right = mergeSortList(fast);
        //merge 有序的左右部分
        return merge(left, right);
    }

    private ListNode merge(ListNode L1, ListNode L2)
    {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        while (L1 != null && L2 != null)
        {
            if(L1.val <= L2.val)
            {
                current.next = L1;
                L1 = L1.next;
            }else {
                current.next = L2;
                L2 = L2.next;
            }
            current = current.next;
        }

        if(L1 != null)
        {
            current.next = L1;
        }

        if(L2 != null)
        {
            current.next = L2;
        }

        return dummyHead.next;
    }


    /**
     *
     * 23. Merge k Sorted Lists
     * DescriptionHintsSubmissionsDiscussSolution
     * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     *
     * Example:
     *
     * Input:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * Output: 1->1->2->3->4->4->5->6
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {

        if(lists == null || lists.length == 0)return null;
        int len = lists.length;
        int interval = 1;
        while ( interval < len)
        {
            for (int i = 0; i < len - interval; i = i + interval*2) {
                lists[i] = merge(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }



    /**
     *
     * https://leetcode.com/problems/middle-of-the-linked-list/description/
     *876. Middle of the Linked List
     * Given a non-empty, singly linked list with head node head, return a middle node of linked list.
     *
     * If there are two middle nodes, return the second middle node.
     *
     *
     *
     * Example 1:
     *
     * Input: [1,2,3,4,5]
     * Output: Node 3 from this list (Serialization: [3,4,5])
     * The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
     * Note that we returned a ListNode object ans, such that:
     * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.
     * Example 2:
     *
     * Input: [1,2,3,4,5,6]
     * Output: Node 4 from this list (Serialization: [4,5,6])
     * Since the list has two middle nodes with values 3 and 4, we return the second one.
     *
     *
     * Note:
     *
     * The number of nodes in the given list will be between 1 and 100.
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        if(head == null || head.next == null)return head;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
        }
        if(fast.next == null)
            return slow;
        return slow.next;
    }

    public ListNode middleNode2(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode middleNode3(ListNode head) {
        ListNode slow = head, fast = head, pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre;
    }
    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null)return;
        ListNode middle = middleNode3(head);
        ListNode right = middle.next;
        middle.next = null;
        right = reverse(right);
        ListNode left = head;
        while (left.next != null && right.next != null)
        {
            ListNode leftNext = left.next;
            ListNode rightNext = right.next;
            left.next = right;
            right.next = leftNext;
            left = leftNext;
            right = rightNext;
        }
        left.next = right;

    }

    /**
     *https://leetcode.com/problems/swap-nodes-in-pairs/description/
     * 24. Swap Nodes in Pairs
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a linked list, swap every two adjacent nodes and return its head.
     *
     * Example:
     *
     * Given 1->2->3->4, you should return the list as 2->1->4->3.
     * Note:
     *
     * Your algorithm should use only constant extra space.
     * You may not modify the values in the list's nodes, only nodes itself may be changed.
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)return head;
        ListNode dummyHead = new ListNode(0);
        ListNode pre = dummyHead;
        ListNode slow = head;
        ListNode fast = slow.next;
        while (fast != null)
        {

            ListNode next = fast.next;
            pre.next = fast;
            slow.next = next;
            fast.next = slow;
            pre = slow;
            slow = next;
            if(slow != null)
            {
               fast = slow.next;
            }else {
                break;
            }
        }

        return dummyHead.next;

    }

    //method 2
    public static ListNode swapPairs2(ListNode head)
    {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        while (head != null && head.next != null)
        {
            pre.next = head.next;
            ListNode t = head.next.next;
            pre.next.next = head;
            pre = pre.next.next;
            head = t;
        }
        pre.next = head;
        return dummy.next;
    }


    // 交换值
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null)return head;
        ListNode slow = head, fast = head.next;
        while (fast != null)
        {
            if(slow.val != fast.val)
            {
                slow = slow.next;
                slow.val = fast.val;

            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }

    //移动指针
    public ListNode deleteDuplicates11(ListNode head) {

        if(head == null)return head;
        ListNode pre = head;
        while (pre.next != null)
        {
            if(pre.val == pre.next.val)
            {
                pre.next = pre.next.next;
            }else {
                pre = pre.next;
            }
        }
        return head;
    }


    /**
     *
     * 82. Remove Duplicates from Sorted List II
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
     *
     * Example 1:
     *
     * Input: 1->2->3->3->4->4->5
     * Output: 1->2->5
     * Example 2:
     *
     * Input: 1->1->1->2->3
     * Output: 2->3
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next == null)return head;
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        boolean flag = false;
        while (head.next != null)
        {
            if(head.val == head.next.val)
            {
                flag = true;
                head.next = head.next.next;
            }else {
               if(flag)
               {
                   head = head.next;
                   flag = false;
                   pre.next = head;
               }else {
                   pre.next = head;
                   pre = pre.next;
                   head = head.next;
               }


            }
        }
        if(flag)
        {
            pre.next = null;
        }
        return dummy.next;
    }


    /**
     *
     *
     * https://leetcode.com/problems/odd-even-linked-list/
     * 328. Odd Even Linked List
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
     *
     * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
     *
     * Example 1:
     *
     * Input: 1->2->3->4->5->NULL
     * Output: 1->3->5->2->4->NULL
     * Example 2:
     *
     * Input: 2->1->3->5->6->4->7->NULL
     * Output: 2->3->6->7->1->5->4->NULL
     * Note:
     *
     * The relative order inside both the even and odd groups should remain as it was in the input.
     * The first node is considered odd, the second node even and so on ...
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null)return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode evenHead = even;
        while (odd.next != null && odd.next.next != null)
        {
            odd.next = odd.next.next;
            odd = odd.next;
            even.next = odd.next;
            if(even.next != null)
            {
                even = even.next;
            }

        }

        odd.next = evenHead;
        return dummyHead.next;
    }



    public static void main(String[] args) {
//        ListNode root = new ListNode(1);
//        ListNode.insert(root,2);
//        ListNode.insert(root,3);
        //head.insert(5);
//        ListNode.insert(root, 2);
//        ListNode.insert(root, 1);
//        ListNode.printListNode(root);
//        System.out.println();

        // root =  ListNode.reverse(root);
//        head.printListNode(root);
//        System.out.println();
//        root = head.reverse(root);
//        head.printListNode(root);
//        ListNode middle = head.findMiddle(root);
//        System.out.println();
//        System.out.print(" middle: ");
//        head.printListNode(middle);
//        middle = head.reverse(middle);
//        System.out.println();
//        head.printListNode(middle);
//        System.out.println(ListNode.isPalindrome(root));
//
        ListNode root2 = new ListNode(1);
        ListNode.insert(root2, 2);
        ListNode.insert(root2, 3);
        ListNode.insert(root2, 4);
        ListNode.insert(root2, 5);
        ListNode.insert(root2, 6);
        root2 =  ListNode.reverse2(root2);
        ListNode.printListNode(root2);
        System.out.println();
//        root2 = ListNode.reverseBetween(root2, 3,6);
//        ListNode.printListNode(root2);
//
//        ListNode listNode = new ListNode(10);
//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            ListNode.insert(listNode, random.nextInt(10));
//        }
//        System.out.println();
//        ListNode.printListNode(listNode);
//        ListNode newnode = ListNode.partition(listNode, 5);
//        System.out.println();
//        ListNode.printListNode(newnode);

//
//        root = removeNthFromEnd(root, 5);
//        ListNode.printListNode(root);

//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node3;
//
//        System.out.println(hasCycle(node1));

//        ListNode root = new ListNode(1);
//        ListNode.insert(root,3);
//        ListNode.insert(root,5);
//
//        ListNode node1 = new ListNode(2);
//        ListNode node2 = new ListNode(4);
//        ListNode node3 = new ListNode(6);
//        ListNode node4 = new ListNode(8);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        ListNode newnode = mergeTwoLists(node1, root);
//        ListNode.printListNode(newnode);

//        ListNode root2 = new ListNode(0);
//        ListNode.insert(root2, 1);
//        ListNode.insert(root2, 2);
//        root2 = rotateRight(root2,4);
//        printListNode(root2);
//
//        ListNode root2 = new ListNode(1);
//        ListNode.insert(root2, 2);
//        ListNode.insert(root2,3);
//        ListNode.insert(root2,4);
//        ListNode.insert(root2,5);
//
//       // root2.reorderList(root2);
//       // root2 = swapPairs(root2);
//       // root2 =  root2.deleteDuplicates2(root2);
//        root2 = root2.oddEvenList(root2);
//        printListNode(root2);

    }
}
