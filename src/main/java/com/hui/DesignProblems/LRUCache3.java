package com.hui.DesignProblems;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/13 17:52
 */
public class LRUCache3<K,V> extends LinkedHashMap<K,V> {
    private  final int maxSize;
    public LRUCache3(int maxSize)
    {
        super(maxSize, 0.75f, true);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size() > maxSize;
    }

    public static void main(String[] args) {
        LRUCache3<Integer,Integer> lruCache3 = new LRUCache3<>(4);
        for (int i = 0; i < 10; i++) {
            lruCache3.put(i,i);
        }
        System.out.println(lruCache3.keySet());
    }
}
