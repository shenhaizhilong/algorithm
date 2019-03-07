package com.hui.algorithm;

import java.util.Arrays;

/**
 * Introduction to Algorithms
 * by Thomas H. Cormen, Charles E. Leiserson, and Ronald L. Rivest. chapter six
 * @author: shenhaizhilong
 * @date: 2018/7/6 21:02
 */
public class BinaryHeapPriorityQueue<T extends Comparable<? super T>> {

    /**
     * The number of elements in the priority queue.
     */
    private int currentSize;

    /**
     * Priority queue represented as a balanced binary heap: the two
     * children of queue[n] are queue[2*n+1] and queue[2*(n+1)].
     * The element with the lowest value is in queue[0], assuming the queue is nonempty.
     */
    private T[] values;

    /**
     *  default initial capacity 16
     *
     */
    private static final int DEFAULT_CAPACITY = 1<<4;

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;


    public BinaryHeapPriorityQueue(int capacity)
    {
        if(capacity < 1)
        {
            throw new IllegalArgumentException("capacity must be bigger than 1");
        }
        currentSize = 0;
        values = (T[]) new Comparable[capacity];
    }


    /**
     * Creates a {@code BinaryHeapPriorityQueue} with the default initial
     *      * capacity (16)
     */
    public BinaryHeapPriorityQueue()
    {
       this(DEFAULT_CAPACITY);
    }

    /**
     * root is 0
     * get parent's index
     *
     * @param i
     * @return
     */
    private int parent(int i) {
        return (i -1)>>>1;
    }

    /**
     * get left child's index
     *
     * @param i
     * @return
     */
    private int left(int i) {
        return (i<<1) + 1;
    }


    // 让根节点为i的子树重新遵循最大堆的性质, 循环法
    private void maxHeapify(int i) {

        int leftChildIndex;
        int largestIndex ;
        int rightChildIndex;
        while (true)
        {
            leftChildIndex = left(i);
            rightChildIndex = leftChildIndex + 1;
            if(leftChildIndex < currentSize && values[leftChildIndex].compareTo(values[i]) > 0)
            {
                largestIndex = leftChildIndex;
            }else {
                largestIndex = i;
            }

            if(rightChildIndex < currentSize && values[rightChildIndex].compareTo(values[largestIndex]) > 0 )
            {
                largestIndex = rightChildIndex;
            }

            if(largestIndex == i)
            {
                break;
            }
            swap(values, i, largestIndex);
            // 交换后，可能违背最大堆的性质，因此需要对子树进行判断，这时largestIndex 不是left Index, 就是right Index
            i = largestIndex;

        }
    }


    private void swap(T[] arrays, int i, int j)
    {
        T temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }

    /**
     *
     * @return max value of max heap
     */
    public T getMax()
    {
        return values[0];
    }

    /**
     *
     * @return return and delete the max value from the max heap.
     * if max heap is  empty, return null
     */
    public T deleteMax()
    {
        if(isEmpty())
        {
            return null;
        }

        T max = values[0];
        values[0] = values[currentSize -1];
        currentSize = currentSize -1;
        maxHeapify(0);
        return max;
    }

    /**
     * insert key to binary heap.
     * @param key
     */
    public void insert(T key)
    {
        if(key == null)
        {
            throw new NullPointerException("key can't be null");
        }
        if(currentSize == 0)
        {
            currentSize++;
            values[0] = key;
            return;
        }


        if(currentSize >= values.length)
        {
            enlargeArray(values.length);
        }

        currentSize++;
        values[currentSize -1] = key;
        increaseKey(currentSize -1, key);

    }

    /**
     * enlarge array
     * @param oldCapacity
     */
    private void enlargeArray(int oldCapacity)
    {

        // Double size if length < 64; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                oldCapacity : (oldCapacity >> 1));

        //over flow
        if(newCapacity < 0)
            throw new OutOfMemoryError();
        // overflow-conscious code
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(oldCapacity);

       values =  Arrays.copyOf(values, newCapacity);
    }


    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     *  increase the value to key in index i, key must be bigger than value[i]
     *  key can't be null
     * @param i
     * @param key
     */
    public void  increaseKey(int i, T key)
    {
        if(key == null || i >= currentSize || i < 0)
        {
            throw new IllegalArgumentException("key can't be null");
        }
        if(currentSize == 0)
        {
            currentSize++;
            values[0] = key;
            return;
        }


        if(key.compareTo(values[i]) < 0)
        {
            throw new IllegalArgumentException("key must be bigger  or equal than the value in index " + i );
        }

        values[i] = key;
        while (i>0 && values[parent(i)].compareTo(values[i]) < 0)
        {
            swap(values, i, parent(i));
            i = parent(i);
        }
    }


    /**
     * check whether the binary is empty
     * @return
     */
    public boolean isEmpty()
    {
        return currentSize == 0;
    }

    public int size()
    {
        return currentSize;
    }
    public void clear()
    {
        // clear to  let gc do its work.
        for (int i = 0; i < currentSize; i++) {
            values[i] = null;
        }
        currentSize = 0;


    }
}
