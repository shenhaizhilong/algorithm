package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/16 20:12
 *
 * 两个链表相交
 *请找到两个链表的第一个公共结点
 *
 * 设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，可知 a + c + b = b + c + a。
 *
 * 当访问链表 A 的指针访问到链表尾部时，令它从链表 B 的头部重新开始访问链表 B；同样地，当访问链表 B 的指针访问到链表尾部时，令它从链表 A 的头部重新开始访问链表 A。这样就能控制访问 A 和 B 两个链表的指针能同时访问到交点。
 *
 *
 */
public class FindFirstCommonNode {

    public ListNode findFirstCommonNode(ListNode A, ListNode B)
    {
        ListNode pA = A;
        ListNode pB = B;
        while (pA != pB)
        {
            pA = pA == null ? B: pA.next;
            pB = pB == null ? A: pB.next;
        }
        return pA;
    }
}
