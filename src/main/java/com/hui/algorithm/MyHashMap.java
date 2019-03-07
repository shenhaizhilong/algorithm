package com.hui.algorithm;

/**
 *
 *
 * 706. Design HashMap
 * DescriptionHintsSubmissionsDiscussSolution
 * Design a HashMap without using any built-in hash table libraries.
 *
 * To be specific, your design should include these functions:
 *
 * put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
 * get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
 *
 * Example:
 *
 * MyHashMap hashMap = new MyHashMap();
 * hashMap.put(1, 1);
 * hashMap.put(2, 2);
 * hashMap.get(1);            // returns 1
 * hashMap.get(3);            // returns -1 (not found)
 * hashMap.put(2, 1);          // update the existing value
 * hashMap.get(2);            // returns 1
 * hashMap.remove(2);          // remove the mapping for 2
 * hashMap.get(2);            // returns -1 (not found)
 *
 * Note:
 *
 * All keys and values will be in the range of [0, 1000000].
 * The number of operations will be in the range of [1, 10000].
 * Please do not use the built-in HashMap library.
 *
 * @author: shenhaizhilong
 * @date: 2018/9/2 19:34
 */
class MyHashMap {

    private final int Len = (1<<16);
    final Node[] buckets = new Node[Len];

    public void put(int key, int value) {
        int i = hash(key);
        if (buckets[i] == null)
        {
            buckets[i] =  new Node(0, 0);
        }
        Node pre = findPre(i, key);

        if(pre.next != null)
        {
            pre.next.val = value;
        }else {
            pre.next = new Node(key,value);
        }

    }

    public int get(int key) {
        int i = hash(key);
        if (buckets[i] == null)
            return -1;
        Node node = findPre(i, key);
        return node.next == null ? -1 : node.next.val;
    }

    public void remove(int key) {
        int i = hash(key);
        if (buckets[i] == null) return;
        Node prev = findPre(i, key);
        if (prev.next == null) return;
        prev.next = prev.next.next;


    }

   private int hash(int key) { return key &(Len -1);}

   private  Node findPre(int hash, int key) {
        Node node = buckets[hash], prev = null;
        while (node != null && node.key != key) {
            prev = node;
            node = node.next;
        }
        return prev;
    }


  private class Node {
        int key, val;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }


    public static void main(String[] args) {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        System.out.println(hashMap.get(1));          // returns 1
        System.out.println(hashMap.get(2));
        System.out.println(hashMap.get(3));            // returns -1 (not found)
        hashMap.put(2, 1);          // update the existing value
       // System.out.println(hashMap.get(2));            // returns 1
        hashMap.remove(2);          // remove the mapping for 2
//        // returns -1 (not found)
        System.out.println(hashMap.get(2));
    }
}


