package com.hui.UnionFindD;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/2 20:45
 *
 * union by size
 *
 */
public class UnionFindII {

    private int count;
    private int[] parents;
    private int[] size;

    // init
    public UnionFindII(int capacity)
    {
        this.count = capacity;
        parents = new int[count];
        size = new int[count];
        for (int i = 0; i < capacity; i++) {
            parents[i] = i;
            size[i] = 1;
        }

    }

    // find it's parent
    public int find(int i)
    {
        while (parents[i] != i)
        {
            parents[i] = parents[parents[i]];
            i = parents[i];
        }
        return parents[i];
    }

    // union by size
    public boolean union(int p, int q)
    {
        int pp = find(p);
        int pq = find(q);
        if(pp == pq)return false;
        if(size[pp] < size[pq])
        {
            parents[pp] = pq;
            size[pq] += size[pp];
        }else {
            parents[pq] = pp;
            size[pp] += size[pq];
        }
        return true;
    }


    // judge whether p and q is in the same set.
    public boolean isConnected(int p, int q)
    {
        int pp = find(p);
        int pq = find(q);
        return pp == pq;
    }

    /**
     *
     * @return  the count of set
     */
    public int getCount()
    {
        return this.count;
    }

    /**
     *
     * @param i
     * @return  the size of set which i belong to
     */
    public int getSize(int i)
    {
        return size[find(i)];
    }

    // re -init
    public void clear()
    {
        for (int i = 0; i < count; i++) {
            parents[i] = i;
            size[i] = 1;
        }
    }
}
