package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/18 13:03
 */
public class DesignCircularDeque2 {

    private int front;
    private int rear;
    private final int[] vals;
    private int size;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public DesignCircularDeque2(int k) {
        vals = new int[k];
        front = 0;
        rear = -1;
        size = 0;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(size == vals.length)return false;
        front = (front -1 + vals.length) % vals.length;
        vals[front] = value;
        size++;

        // since rear init to -1, so we need update rear
        if(size == 1) rear = front;
        return true;

    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(size == vals.length)return false;
        rear = (rear + 1) % vals.length;
        vals[rear] = value;
        size++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(size == 0)return false;
        front = (front + 1) % vals.length;
        size--;
        return true;

    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(size == 0)return false;
        rear = (rear - 1 + vals.length) % vals.length;
        size--;
        return true;

    }

    /** Get the front item from the deque. */
    public int getFront() {
        if(isEmpty())return -1;
        return vals[front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if(isEmpty())return -1;
        return vals[rear];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;

    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == vals.length;
    }


}
