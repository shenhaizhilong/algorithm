package com.hui.algorithm;

/**
 *
 *
 * 641. Design Circular Deque
 * DescriptionHintsSubmissionsDiscussSolution
 * Design your implementation of the circular double-ended queue (deque).
 *
 * Your implementation should support following operations:
 *
 * MyCircularDeque(k): Constructor, set the size of the deque to be k.
 * insertFront(): Adds an item at the front of Deque. Return true if the operation is successful.
 * insertLast(): Adds an item at the rear of Deque. Return true if the operation is successful.
 * deleteFront(): Deletes an item from the front of Deque. Return true if the operation is successful.
 * deleteLast(): Deletes an item from the rear of Deque. Return true if the operation is successful.
 * getFront(): Gets the front item from the Deque. If the deque is empty, return -1.
 * getRear(): Gets the last item from Deque. If the deque is empty, return -1.
 * isEmpty(): Checks whether Deque is empty or not.
 * isFull(): Checks whether Deque is full or not.
 *
 *
 * Example:
 *
 * MyCircularDeque circularDeque = new MycircularDeque(3); // set the size to be 3
 * circularDeque.insertLast(1);			// return true
 * circularDeque.insertLast(2);			// return true
 * circularDeque.insertFront(3);			// return true
 * circularDeque.insertFront(4);			// return false, the queue is full
 * circularDeque.getRear();  			// return 2
 * circularDeque.isFull();				// return true
 * circularDeque.deleteLast();			// return true
 * circularDeque.insertFront(4);			// return true
 * circularDeque.getFront();			// return 4
 *
 *
 * Note:
 *
 * All values will be in the range of [0, 1000].
 * The number of operations will be in the range of [1, 1000].
 * Please do not use the built-in Deque library.
 *
 * @author: shenhaizhilong
 * @date: 2018/10/18 12:19
 */
public class DesignCircularDeque {


    private int front;
    private int rear;
    private final int[] vals;
    private int size;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public DesignCircularDeque(int k) {
        vals = new int[k];
        front = k -1;
        rear = 0;
        size = 0;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(size == vals.length)return false;
        vals[front] = value;
        front = (front -1 + vals.length) % vals.length;
        size++;
        return true;

    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(size == vals.length)return false;
        vals[rear] = value;
        rear = (rear + 1) % vals.length;
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
      int idx = (front + 1) %vals.length;
      return vals[idx];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if(isEmpty())return -1;
        int idx = (rear - 1 + vals.length) %vals.length;
        return vals[idx];
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
