package com.hui.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 146 LRU Cache
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LRUCache cache = new LRUCache( capacity );
        *
        *cache.put(1,1);
        *cache.put(2,2);
        *cache.get(1);       // returns 1
        *cache.put(3,3);    // evicts key 2
        *cache.get(2);       // returns -1 (not found)
        *cache.put(4,4);    // evicts key 1
        *cache.get(1);       // returns -1 (not found)
        *cache.get(3);       // returns 3
        *cache.get(4);       // returns 4
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/10/18 9:17
 */
public class LRUCache {

    private Map<Integer, DoubleListNode> cache;
    private int Capacity;
    private int size;
    private DoubleListNode head;
    private DoubleListNode tail;

    public LRUCache(int capacity) {

        this.Capacity = capacity;
        this.size = 0;
        this.cache = new HashMap<>(capacity);

    }

    public int get(int key) {
        DoubleListNode node = cache.get(key);
        if(node == null)
        {
            return -1; // key not found.
        }

        // key found and move it to the head
        this.moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {

        DoubleListNode node = cache.get(key);

        // not find it int the cache
        if(node == null)
        {
            DoubleListNode newNode = new DoubleListNode();
            newNode.key = key;
            newNode.value = value;
            this.cache.put(key, newNode);
            this.addNode(newNode);
            size++; // add a new node, increase it.
            if(size > Capacity)
            {
                // delete the tail
                DoubleListNode temp = this.popTail();
                this.cache.remove(temp.key);
                size--;
            }
        }else {
            // find the key in cache,  update the node's value and move it to the head.
            node.value = value;
            this.moveToHead(node);
        }
    }

    private static class DoubleListNode{
        DoubleListNode pre;
        DoubleListNode next;
        int key;
        int value;
    }

    /**
     *
     * always add node at the head
     * @param node
     */
    private void addNode(DoubleListNode node)
    {
        if(head == null)
        {
            head = node;
            tail = node;
        }

        node.next = head;
        head.pre = node;
        head = node;
    }

    /**
     * remove an existing node from the double list node.
     * @param node
     */
    private void removeNode(DoubleListNode node)
    {
        if(node == head && node == tail)
        {
            head = null;
            tail = null;
            return;
        }

        if(node == head)
        {

            head = head.next;
            head.pre = null;
            return;
        }

        if(node == tail)
        {
            tail = tail.pre;
            tail.next = null;
            return;
        }

        DoubleListNode pre = node.pre;
        pre.next = node.next;
        node.next.pre = pre;


    }


    /**
     *
     * move an existing node to the linked list's head.
     * @param node
     */
    private void moveToHead(DoubleListNode node)
    {
        if(node == head)return;
        this.removeNode(node);
        this.addNode(node);
    }


    /**
     *  return current tail node and remove tail node from linked list
     * @return
     */
    private DoubleListNode popTail()
    {
        DoubleListNode res = tail;
        this.removeNode(tail); // remove tail node.
        return res;
    }


    public static void main(String[] args) {


        LRUCache cache = new LRUCache( 2 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4

    }
}
