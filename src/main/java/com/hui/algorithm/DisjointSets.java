package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/7 19:26
 */
public class DisjointSets {

    private int[] parents;

    public DisjointSets(int numberElements)
    {
        parents = new int[numberElements];

        // initialize all elements to -1
        for (int i = 0; i < numberElements; i++) {
            parents[i] = -1;
        }
    }

    /**
     *  find a set which x belong to.
     * @param x
     * @return the index of the root
     */
    public int find(int x)
    {
        rangeCheck(0, parents.length -1, x);
           int k;
           while ((k = parents[x]) >= 0 )
           {
               x = k;
           }
           return x;
    }

    /**
     * union two disjoint sets,we assume root1 and root2 are distinct and represent set names
     * @param root1 the root of set 1
     * @param root2 the root of set 2
     */
    public void union(int root1, int root2)
    {
        rangeCheck(0, parents.length -1, root1);
        rangeCheck(0, parents.length -1, root2);
        if(root1 != root2)
        {
            parents[root1] = parents[root1] + parents[root2];
            parents[root2] = root1;
        }

    }

    /**
     * less nodes set join to large nodes set
     * we assume x and y are distinct and represent set names
     * @param x
     * @param y
     */
    public void unionBySize(int x, int y)
    {
        int root1 = find(x);
        int root2 = find(y);
        if(root1 != root2)
        {
            int temp = parents[root1] + parents[root2];
            if(parents[root1] < parents[root2])
            {
                // make root1 as the new root
                parents[root1] = temp;
                parents[root2] = root1;
            }else {
                // make root2 as the new root
                parents[root2] = temp;
                parents[root1] = root2;
            }
        }


    }


    /**
     * union two disjoint sets using the height heuristic
     * we assume root1 and root2 are distinct and represent set names
     * @param root1  the root of set1
     * @param root2  the root of set2
     */
    public void unionByHeight(int root1, int root2)
    {
        rangeCheck(0, parents.length -1, root1);
        rangeCheck(0, parents.length -1, root2);
        if(parents[root2] < parents[root1])  // root2 is deeper
        {
            parents[root1] = root2; // make root2 as the new root
        }else {                         // root1 is deeper
            if(parents[root1] == parents[root2])
            {
                parents[root1]--; // update height if same
            }

            parents[root2] = root1; // make root1 as the new root
        }
    }
    /**
     * check whether index x out of bounds
     * @param startIndex  array start index
     * @param endIndex    array end index
     * @param x  index to check
     */
    private void rangeCheck(int startIndex, int endIndex, int x)
    {
        if(x < startIndex || x > endIndex)
        {
            throw new IndexOutOfBoundsException("index x out of bounds");
        }
    }

    /**
     * compress the path when we find the root of x
     * all the nodes in this path will point to root
     * @param x
     * @return
     */
    public int collapsingFind(int x)
    {
        int root,temp;
        // find the root
        root = find(x);
        while (x != root)
        {
            temp = parents[x];
            //all the nodes in this path will point to root
            parents[x] = root;
            x = temp;
        }
        return root;
    }

    public void print()
    {
        System.out.println(Arrays.toString(parents));
    }

}
