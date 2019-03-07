package com.hui.algorithm;



/**
 * @author: shenhaizhilong
 * @date: 2018/11/30 15:44
 *
 *765. Couples Holding Hands
 * DescriptionHintsSubmissionsDiscussSolution
 * N couples sit in 2N seats arranged in a row and want to hold hands. We want to know the minimum number of swaps so that every couple is sitting side by side. A swap consists of choosing any two people, then they stand up and switch seats.
 *
 * The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).
 *
 * The couples' initial seating is given by row[i] being the value of the person who is initially sitting in the i-th seat.
 *
 * Example 1:
 *
 * Input: row = [0, 2, 1, 3]
 * Output: 1
 * Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
 * Example 2:
 *
 * Input: row = [3, 2, 0, 1]
 * Output: 0
 * Explanation: All couples are already seated side by side.
 * Note:
 *
 * len(row) is even and in the range of [4, 60].
 * row is guaranteed to be a permutation of 0...len(row)-1.
 *
 *
 */
public class CouplesHoldingHands {


    public int minSwapsCouples(int[] row) {
            return dfs(row, 0);
    }

    private int dfs(int[] row, int start)
    {
        while (start < row.length -1)
        {
            if((row[start] & 0x01) == 1 && row[start +1] == row[start] -1)
            {
                start += 2;
            }else if ((row[start] & 0x01) == 0 && row[start +1] == row[start] +1){
                start += 2;
            }else break;
        }
        if(start >= row.length)return 0;



        int nextHalf = row[start] -1;
        if((row[start] & 0x01) != 1)
        {
            nextHalf = row[start] +1;
        }

        int min = Integer.MAX_VALUE;
        for (int i =  start +1; i < row.length; i++) {
            if(row[i] == nextHalf)
            {
                swap(row, i, start +1);
                int next = dfs(row, start +2);
                swap(row, i, start +1);
                min = Math.min(min, next +1);
                break;
            }
        }

        return min;

    }

    private void swap(int[] row, int i, int j)
    {
        int temp = row[i];
        row[i] = row[j];
        row[j] = temp;
    }


    public int minSwapsCouples2(int[] row) {
        int size = row.length/2;
        UnionFind unionFind = new UnionFind(size);
        for (int i = 0; i < size; i++) {
            int left = row[2*i];
            int right = row[2*i +1];
            unionFind.union(left/2, right/2);
        }
        return size - unionFind.getCount();


    }


    private class UnionFind
    {
        private int [] parents;
        private int count;
        public UnionFind(int size)
        {
            this.count = size;
            parents = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
            }
        }

        public int find(int i)
        {
            while (parents[i] != i)
            {
                parents[i] = parents[parents[i]];// // path compression by halving
                i = parents[i];
            }
            return i;
        }

        public void union(int i, int j)
        {
            int a = find(i);
            int b = find(j);
            if(a != b)
            {
                parents[b] = a;
                count--;
            }
        }

        public int getCount()
        {
            return count;
        }

        public boolean isConnected(int p, int q)
        {
            int pp = find(p);
            int pq = find(q);
            return pp == pq;
        }



    }

    public static void main(String[] args) {


        CouplesHoldingHands couplesHoldingHands = new CouplesHoldingHands();
       // System.out.println(couplesHoldingHands.minSwapsCouples(new int[]{3,2,0,1}));
       // System.out.println(couplesHoldingHands.minSwapsCouples(new int[]{0,2,1,3}));
        System.out.println(couplesHoldingHands.minSwapsCouples(new int[]{0,2,4,1,3,5}));
        System.out.println(couplesHoldingHands.minSwapsCouples(new int[]{28,4,37,54,35,41,43,42,45,38,19,51,49,17,47,25,12,53,57,20,2,1,9,27,31,55,32,48,59,15,14,8,3,7,58,23,10,52,22,30,6,21,24,16,46,5,33,56,18,50,39,34,29,36,26,40,44,0,11,13}));

        System.out.println(couplesHoldingHands.minSwapsCouples2(new int[]{0,2,4,1,3,5}));
        System.out.println(couplesHoldingHands.minSwapsCouples2(new int[]{28,4,37,54,35,41,43,42,45,38,19,51,49,17,47,25,12,53,57,20,2,1,9,27,31,55,32,48,59,15,14,8,3,7,58,23,10,52,22,30,6,21,24,16,46,5,33,56,18,50,39,34,29,36,26,40,44,0,11,13}));

    }

}
