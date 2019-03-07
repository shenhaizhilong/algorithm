package com.hui.algorithm;
import java.util.*;

/**
 *
 * https://leetcode-cn.com/problems/min-stack/description/
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2
 *
 *
 * ps: 不论是最小栈还是最大栈，我们都可以用两个栈来实现
 * 第一个栈存放原始数据，第二个栈存放最小值的数据
 * push: 当要插入的值，小于等于第二个栈栈顶的数据时，在第一、第二栈插入该数据，否则，
 * 只需要在第一个栈中插入该数据
 *
 * pop:  当要删除的值大于第二个栈栈顶的数据的时，第一个栈直接删除；
 * 当要删除的值== 第二个栈栈顶的数据时，第一、二栈都删除
 * @author: shenhaizhilong
 * @date: 2018/8/14 10:53
 */
public class MinStack {

    private List<Integer> stack;
    private List<Integer> minStack;
    /** initialize your data structure here. */
    public MinStack() {

        stack = new ArrayList<>();
        minStack = new ArrayList<>();
    }

    public void push(int x) {
        stack.add(x);

        if(minStack.isEmpty() || x < stack.get(minStack.get(minStack.size() -1)))
        {
            minStack.add(stack.size() -1);
        }
    }

    public void pop() {
        if(stack.isEmpty() || minStack.isEmpty())return;
        int index = minStack.get(minStack.size() -1);
        if(stack.size() == index +1)
        {
            minStack.remove(minStack.size() -1);
        }

        stack.remove(stack.size() -1);

    }

    public int top()throws Exception {
        if(stack.isEmpty())
        {
            throw new Exception("Stack is null");
        }

        return stack.get(stack.size() -1);
    }

    public int getMin()throws Exception {
       if(stack.isEmpty())
       {
           throw new Exception("stack is null");
       }

       int minIndex = minStack.get(minStack.size() -1);
       return stack.get(minIndex);
    }

    public boolean isEmpty()
    {
        return stack.isEmpty();
    }


    public static void main(String[] args) {


        MinStack minStack = new MinStack();
//        minStack.push(3);
//        minStack.push(2);
//        minStack.push(1);
//        minStack.push(1);
//        minStack.push(1);
//        try
//        {
//            System.out.println(minStack.getMin());
//            minStack.pop();
//            System.out.println(minStack.getMin());
//            minStack.pop();
//            System.out.println(minStack.getMin());
//            minStack.pop();
//            System.out.println(minStack.getMin());
//            minStack.pop();
//            System.out.println(minStack.getMin());
//        }catch (Exception ex)
//        {
//
//        }

        minStack.push(-2);
        minStack.push(0);
        minStack.push(-1);
        try
        {
            System.out.println(minStack.getMin());
            minStack.pop();
            minStack.top();
            System.out.println(minStack.getMin());
        }catch (Exception ex)
        {
            System.out.println(ex);
        }


    }
}
