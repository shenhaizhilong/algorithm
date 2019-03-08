package com.hui.DesignProblems;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/22 11:14
 *
 * 460. LFU Cache
 * DescriptionHintsSubmissionsDiscussSolution
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LFUCache cache = new LFUCache( 2  );
        *
        *cache.put(1,1);
        *cache.put(2,2);
        *cache.get(1);       // returns 1
        *cache.put(3,3);    // evicts key 20
        *cache.get(2);       // returns -1 (not found)
        *cache.get(3);       // returns 3.
        *cache.put(4,4);    // evicts key 1.
        *cache.get(1);       // returns -1 (not found)
        *cache.get(3);       // returns 3
        *cache.get(4);       // returns 4
 */
public class LFUCache {


    private int capacity;

    private Map<Integer, Integer> cache;
    private Map<Integer, Node> freqNodeCache;
    private Node head;



    public LFUCache(int capacity) {

        this.cache = new HashMap<>(capacity);
        this.capacity = capacity;
        freqNodeCache = new HashMap<>();

    }


    public int get(int key) {

        if(cache.containsKey(key))
        {
            increaseFreq(key);
            return cache.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if(this.capacity < 1)return;
        if(cache.containsKey(key))
        {
            cache.put(key, value);
        }else {
            if(cache.size() == this.capacity)
            {
                removeOld();
            }
            cache.put(key, value);
            addToHead(key);
        }

        increaseFreq(key);
    }

    private void increaseFreq(int key)
    {
        Node node = freqNodeCache.get(key);
        node.keys.remove(key);
        if(node.next == null)
        {
            node.next = new Node(node.freq + 1);
            node.next.pre = node;
            node.next.keys.add(key);
        }else if( node.next.freq == node.freq + 1)
        {
            node.next.keys.add(key);
        }else {
            Node tmp = new Node(node.freq + 1);
            tmp.keys.add(key);
            tmp.pre = node;
            tmp.next = node.next;
            node.next.pre = tmp;
            node.next = tmp;
        }

        freqNodeCache.put(key, node.next);
        if(node.keys.size() == 0)removeNode(node);

    }

    private void  addToHead(int key)
    {
        if(head == null)
        {
            head = new Node(0);
            head.keys.add(key);
        }else if(head.freq > 0)
        {
            Node node = new Node(0);
            node.keys.add(key);
            node.next = head;
            head.pre = node;
            head = node;
        }else {
            head.keys.add(key);
        }

        freqNodeCache.put(key, head);

    }

    private void removeOld()
    {
        if(head == null)return;
        int oldKey = 0;
        for (int i :
                head.keys) {
            oldKey = i;
            break;
        }

        head.keys.remove(oldKey);
        if(head.keys.size() == 0)
        {
            removeNode(head);
        }

        cache.remove(oldKey);
        freqNodeCache.remove(oldKey);
    }


    private void removeNode(Node node)
    {
        if(node.pre == null)
        {
            head = node.next;
        }else {
            node.pre.next = node.next;
        }

        if(node.next != null)
        {
            node.next.pre = node.pre;
        }

    }


    private static class Node{
        public int freq;
        public LinkedHashSet<Integer> keys;
        Node pre;
        Node next;
        public Node(int freq)
        {
            this.freq = freq;
            keys = new LinkedHashSet<>();
        }
    }

}
