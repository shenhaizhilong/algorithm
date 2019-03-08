package com.hui.Stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * 225. Implement Stack using Queues
 * DescriptionHintsSubmissionsDiscussSolution
 * Implement the following operations of a stack using queues.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 * Example:
 *
 * MyStack stack = new MyStack();
 *
 * stack.push(1);
 * stack.push(2);
 * stack.top();   // returns 2
 * stack.pop();   // returns 2
 * stack.empty(); // returns false
 * Notes:
 *
 * You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
 * Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
 * You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
 * @author: shenhaizhilong
 * @date: 2018/8/31 11:07
 */
public class MyStack {
    Deque<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(!queue.isEmpty())
        {
            return queue.pollLast();
        }
        return Integer.MIN_VALUE;
    }

    /** Get the top element. */
    public int top() {
        if(!queue.isEmpty())
        {
            return queue.peekLast();
        }
        return Integer.MIN_VALUE;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
