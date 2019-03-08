package com.hui.UnionFindD;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/1 11:50
 *
 * https://en.wikipedia.org/wiki/Disjoint-set_data_structure
 *
 *Using both path compression, splitting, or halving and union by rank or size ensures
 * that the amortized time per operation is only {\displaystyle O(\alpha (n))} O(\alpha (n)),[4][5] which is optimal,
 * [6] where {\displaystyle \alpha (n)} \alpha (n) is the inverse Ackermann function. This function has a value {\displaystyle \alpha (n)<5} {\displaystyle \alpha (n)<5} for any value of n that can be written in this physical universe,
 *  so the disjoint-set operations take place in essentially constant time.
 *
 * union by rank
 *
 */
public class UnionFind {

    private int count;
    private int[] parents;
    private int[] rank;
    public UnionFind(int capacity)
    {
        this.count = capacity;
        parents = new int[count];
        for (int i = 0; i < capacity; i++) {
            parents[i] = i;
        }
        rank = new int[count];
    }

    public int find(int i)
    {
        while (parents[i] != i)
        {
            parents[i] = parents[parents[i]];
            i = parents[i];
        }
        return parents[i];
    }

    public boolean union(int p, int q)
    {
        int pp = find(p);
        int pq = find(q);
        if(pp == pq)return false;
        if(rank[pp] < rank[pq])
        {
            parents[pp] = pq;
        }else {
            if(rank[pp] == rank[pq])
            {
                rank[pp]++;
            }
            parents[pq] = pp;
        }
        return true;
    }

    public boolean isConnected(int p, int q)
    {
        int pp = find(p);
        int pq = find(q);
        return pp == pq;
    }

    public int getCount()
    {
        return this.count;
    }

    // re -init
    public void clear()
    {
        for (int i = 0; i < count; i++) {
            parents[i] = i;
        }
    }
}
