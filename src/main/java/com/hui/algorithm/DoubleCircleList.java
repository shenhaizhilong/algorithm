package com.hui.algorithm;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/2 14:24
 */
public class DoubleCircleList<T extends Comparable< ? super T>> {

    private CirleNode<T> head;
    private CirleNode<T> tail;
    private int size;

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }


    public void insert(T x) {
        CirleNode<T> newnode = new CirleNode<>(x);
        if (head == null) {
            head = newnode;
            head.next = head;
            head.prev = head;
            tail = head;
        }

        tail.next = newnode;
        newnode.prev = tail;
        tail = newnode;
        tail.next = head;
        head.prev = tail;
        size++;

    }

    private DoubleCircleList<T> union(DoubleCircleList<T> other)
    {
        if(this.isEmpty())
        {
            return other;
        }

        if(other == null || other.isEmpty())
        {
            return this;
        }

        CirleNode<T> otherHead = other.head;
        CirleNode<T> otherTail = other.tail;
        tail.next = otherHead;
        otherHead.prev = tail;
        otherTail.next = head;
        tail = otherTail;

        return this;


    }

    public List<T> getList()
    {
        return getList(head);
    }

    private List<T> getList(CirleNode<T> currentNode){
        List<T> list = new ArrayList<>(size);
        CirleNode<T> temp = currentNode;
        while (currentNode != null)
        {
            list.add(currentNode.val);
            currentNode = currentNode.next;
            if(currentNode == temp)
            {
                break;
            }
        }
        return list;
    }

    public List<T> getReverseList()
    {
        return getReverseList(tail);
    }

    private List<T> getReverseList(CirleNode<T> currentNode){
        List<T> list = new ArrayList<>(size);
        CirleNode<T> temp = currentNode;
        while (currentNode != null)
        {
            list.add(currentNode.val);
            currentNode = currentNode.prev;
            if(currentNode == temp)
            {
                break;
            }
        }
        return list;
    }


    private static class CirleNode<T extends Comparable< ? super T>>{
        private T val;
        private CirleNode<T> prev;
        private CirleNode<T> next;

        CirleNode(T x)
        {
            this.val = x;
        }

    }


    public static void main(String[] args) {
        DoubleCircleList<Integer> doubleCircleList = new DoubleCircleList<>();
        for (int i = 0; i < 10; i++) {
            doubleCircleList.insert(i);
        }

        List<Integer> list = doubleCircleList.getList();
        System.out.println(list);

        list.clear();
        list = doubleCircleList.getReverseList();
        System.out.println(list);
        System.out.println(doubleCircleList.getSize());

        DoubleCircleList<Integer> doubleCircleList2 = new DoubleCircleList<>();
        for (int i = 0; i < 10; i++) {
            doubleCircleList2.insert(i*i);
        }

        doubleCircleList = doubleCircleList.union(doubleCircleList2);
        list.clear();
        list = doubleCircleList.getList();
        System.out.println(list);

        list.clear();
        list = doubleCircleList2.getList();
        System.out.println(list);

    }
}
