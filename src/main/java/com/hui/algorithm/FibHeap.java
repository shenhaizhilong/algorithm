package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 *  Introduction to Algorithms Fibonacci heap,waiting to be implemented.
 * @author: shenhaizhilong
 * @date: 2018/8/2 15:52
 */
public class FibHeap<T extends Comparable<? super T>> {

    private FibHeapNode<T> Min;
    private FibHeapNode<T> rootHead;
    private FibHeapNode<T> rootTail;
    private int size;

    public FibHeap()
    {

    }

    public void clear()
    {
        Min = null;
        size = 0;
        rootHead = null;
        rootTail = null;
    }

    public static <T extends Comparable<? super T>> FibHeap<T> union(FibHeap<T> h1, FibHeap<T> h2)
    {
        FibHeap<T> h = new FibHeap<>();
        h.rootHead = circleLinkedListMerge(h1.rootHead, h2.rootHead);
        h.rootTail = h.rootHead.left;
        h.rootTail.right = h.rootHead;

        h.Min = h1.Min;
        if(h1.Min == null || (h2.Min != null && (h2.Min.key).compareTo(h1.Min.key) < 0))
        {
            h.Min = h2.Min;
        }

        h.size = h1.size + h2.size;
        h1.clear();
        h2.clear();
        return h;
    }

    public T getMin()
    {
        return (Min == null) ? null:Min.key;

    }

    public void insert(T x)
    {
        FibHeapNode<T> newnode = new FibHeapNode<>(x);
        rootHead = circleLinklistInsert(rootHead, newnode);
        if(Min == null)
        {
            rootTail = rootHead;
            Min = newnode;
        }else {
            if(newnode.key.compareTo(Min.key) < 0)
            {
                Min = newnode;
            }
        }

        size +=1;
    }

    public List<T> getReverseList()
    {
        return getReverseList(rootTail);
    }

    private List<T> getReverseList(FibHeapNode<T> currentNode){
        List<T> list = new ArrayList<>(size);
        FibHeapNode<T> temp = currentNode;
        while (currentNode != null)
        {
            list.add(currentNode.key);
            currentNode = currentNode.left;
            if(currentNode == temp)
            {
                break;
            }
        }
        return list;
    }

    public List<T> getList()
    {
        return getList(rootHead);
    }

    private List<T> getList(FibHeapNode<T> currentNode){
        List<T> list = new ArrayList<>(size);
        FibHeapNode<T> temp = currentNode;
        while (currentNode != null)
        {
            list.add(currentNode.key);
            currentNode = currentNode.right;
            if(currentNode == temp)
            {
                break;
            }
        }
        return list;
    }

    /**
     * insert newnode to current linklist 's tail location
     * @param current
     * @param newnode
     * @return
     */
    private FibHeapNode<T> circleLinklistInsert(FibHeapNode<T> current, FibHeapNode<T> newnode)
    {
        if(current == null)
        {
             newnode.left = newnode;
             newnode.right = newnode;
             return newnode;
        }

        FibHeapNode<T> head = current;
        FibHeapNode<T> tail = current.left;
        tail.right = newnode;
        newnode.left = tail;
        tail = newnode;
        tail.right = head;
        head.left = tail;
        return head;

    }

    private static <T extends Comparable<? super T>>  FibHeapNode<T> circleLinkedListMerge(FibHeapNode<T> A, FibHeapNode<T> B)
    {
        if(B == null)
        {
            return A;
        }
        if(A == null)
        {
            return B;
        }

        FibHeapNode<T> tailA = A.left;
        FibHeapNode<T> tailB = B.left;
        tailA.right = B;
        B.left = tailA;
        tailB.right = A;
        A.left = tailB;
        return A;
    }

    public int getSize() {
        return size;
    }

    private static class FibHeapNode<T extends Comparable<? super T>>
    {
        private T key;
        private FibHeapNode<T> left;    // a pointer to its left siblings
        private FibHeapNode<T> right;   // a pointer to its right siblings
        private FibHeapNode<T> parent;  // a pointer to its parent
        private FibHeapNode<T> child;   // a pointer to any of its children
        private int degree;             //how many children in x.child
        private boolean mark;           //when it become another node's child, whether it has lost child.

        FibHeapNode(T key)
        {
            this.key = key;
            degree = 0;
            mark = false;
        }
    }

    public static void main(String[] args) {
        FibHeap<Integer> fibHeap1 = new FibHeap<>();
        for (int i = 0; i < 10; i++) {
            fibHeap1.insert(i);
        }


        List<Integer> list = fibHeap1.getList();
        System.out.println(list);
        FibHeap<Integer> fibHeap2 = new FibHeap<>();
        for (int i = 0; i < 10; i++) {
            fibHeap2.insert(i*i);
        }
        list.clear();
        list = fibHeap2.getList();
        System.out.println(list);

        FibHeap<Integer> fibHeap3 = FibHeap.union(fibHeap1, fibHeap2);
        System.out.println(fibHeap1.size);
        System.out.println(fibHeap2.size);
        list.clear();
        list = fibHeap2.getList();
        System.out.println(list);
        System.out.println(fibHeap3.size);
        list = fibHeap3.getList();
        System.out.println(list);

        list.clear();
        list = fibHeap3.getReverseList();
        System.out.println(list);

    }
}
