package com.hui.algorithm;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * http://www.tom-e-white.com/2007/11/consistent-hashing.html
 *
 *
 * Usage：
 * 1.a distributed caching system
 * 2.a distributed searching system
 * 3.a distributed file system
 *
 * Some known instances where consistent hashing is used are:
 *
 * Couchbase automated data partitioning
 * Openstack's Object Storage Service Swift[3]
 * Partitioning component of Amazon's storage system Dynamo[4]
 * Data partitioning in Apache Cassandra[5]
 * Data Partitioning in Voldemort[6]
 * Akka's consistent hashing router[7]
 * Riak, a distributed key-value database[8]
 * GlusterFS, a network-attached storage file system[9]
 * Skylable, an open-source distributed object-storage system [10]
 * Akamai Content Delivery Network [11]
 * Discord chat application [12]
 * Maglev: A Fast and Reliable Software Network Load Balancer
 * @author: shenhaizhilong
 * @date: 2018/7/15 16:32
 */
public class ConsistentHash<T> {


    //a hash function interface, used to specify the hash function
    private final HashFunction hashFunction;

    //every physical node have 211 default virtual nodes, if you didn't specify the numberOfVirtualNodes .
    //As the number of Virtual Nodes increases the distribution of objects becomes more balanced.
    private static final int DefaultNumberOfVirtualNodes = 211;

    private final int numberOfVirtualNodes;

    //The range of hash circle  [-2^31, 2^31 -1]
    private final TreeMap<Integer, T> circle = new TreeMap<>();



    public ConsistentHash(HashFunction hashFunction, int numberOfVirtualNodes, Collection<T> nodesCollection)
    {
        this.hashFunction = hashFunction;
        this.numberOfVirtualNodes = numberOfVirtualNodes;
        for (T n :
                nodesCollection) {
            add(n);
        }
    }

    public ConsistentHash(HashFunction hashFunction,  Collection<T> nodesCollection)
    {
        this.hashFunction = hashFunction;
        this.numberOfVirtualNodes = DefaultNumberOfVirtualNodes;
        for (T n :
                nodesCollection) {
            add(n);
        }
    }


    // get the virtual nodes count from the hash circle
    public int getNodesCount()
    {
        return circle.size();
    }

    // add a physical node to the hash circle.
    public void  add(T node)
    {
        for (int i = 0; i < numberOfVirtualNodes; i++) {
            circle.put(hashFunction.hash(node.toString() + "#" + i), node);
        }
    }

    //remove a physical node from the hash circle.
    public void remove(T node)
    {
        for (int i = 0; i < numberOfVirtualNodes; i++) {
            circle.remove(hashFunction.hash(node.toString() + "#" + i));
        }
    }

    public T get(Object key)
    {
        if(circle.isEmpty())return null;

        int hash = hashFunction.hash(key.toString());

        // Returns the least key greater than or equal to the given key, or null if there is no such key.
        // https://docs.oracle.com/javase/7/docs/api/java/util/TreeMap.html
        Integer successor = circle.ceilingKey(hash);
        if(successor == null)
        {
            successor = circle.firstKey();
        }

        return circle.get(successor);

    }

    public void clear()
    {
        circle.clear();
    }

    public Set<Integer> getKeys()
    {
        return circle.keySet();
    }

    public Set<Map.Entry<Integer, T>> getEntrySet()
    {
        return circle.entrySet();
    }

}
