package com.hui.Array;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/20 19:34
 */
public class FindKPairswithSmallestSums {

    /**
     *
     * 373. Find K Pairs with Smallest Sums
     * DescriptionHintsSubmissionsDiscussSolution
     * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
     *
     * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
     *
     * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
     *
     * Example 1:
     *
     * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
     * Output: [[1,2],[1,4],[1,6]]
     * Explanation: The first 3 pairs are returned from the sequence:
     *              [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
     * Example 2:
     *
     * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
     * Output: [1,1],[1,1]
     * Explanation: The first 2 pairs are returned from the sequence:
     *              [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
     * Example 3:
     *
     * Input: nums1 = [1,2], nums2 = [3], k = 3
     * Output: [1,3],[2,3]
     * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        List<int[]> list = new ArrayList<>();
        PriorityQueue<Tuple> maxHeap = new PriorityQueue<>();

        for (int i = 0; i < nums1.length ; i++) {
            for (int j = 0; j <nums2.length ; j++) {
                if(maxHeap.size() < k)
                {
                    maxHeap.offer(new Tuple(i,j,nums1[i] + nums2[j]));
                }else {
                    if(maxHeap.peek().val > nums1[i] + nums2[j])
                    {
                        maxHeap.poll();
                        maxHeap.offer(new Tuple(i,j, nums1[i] + nums2[j]));
                    }else break;
                }
            }
        }

        int size = maxHeap.size();
        for (int i = size -1; i >=0; i--) {
            Tuple t = maxHeap.poll();
            list.add(0, new int[]{nums1[t.x],nums2[t.y]});
        }

        return list;
    }

    private static class Tuple implements Comparable<Tuple>{
        int x,y;
        int val;
        Tuple(int x,int y, int val)
        {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Tuple o) {
            return   o.val - this.val;
        }
    }


    /**
     * for an example:
     * nums1 = {1,7,11},
     * nums2 = {2,4,6}
     *
     *      2     4      6
     *     ---------------
     * 1  |#
     * 7  |#
     * 11 |#
     *    |
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<int[]> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        List<int[]> list = new ArrayList<>();
        if(nums1.length == 0 || nums2.length == 0)return list;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
            }
        });

        for (int i = 0; i < nums1.length && i < k; i++) {
            minHeap.offer(new int[]{i,0});
        }

        while (k-- > 0 && !minHeap.isEmpty())
        {
            int[] t = minHeap.poll();
            list.add(new int[]{nums1[t[0]], nums2[t[1]]});
            if(t[1] == nums2.length -1)continue; // no more elements in nus2
            minHeap.offer(new int[]{t[0], t[1] + 1});// right direction.
        }

        return list;

    }


    /**
     *
     * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84566/Share-My-Solution-which-beat-96.42
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<int[]> kSmallestPairs3(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Tuple2> pq = new PriorityQueue<>();
        int m = nums1.length, n = nums2.length;
        List<int[]> res = new ArrayList<>();
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k <= 0) return res;
        for(int j = 0; j <= n-1; j++) pq.offer(new Tuple2(0, j, nums1[0]+nums2[j]));
        for(int i = 0; i < Math.min(k, m *n); i++) {
            Tuple2 t = pq.poll();
            res.add(new int[]{nums1[t.x], nums2[t.y]});
            if(t.x == m - 1) continue;  // no more elements in nums1
            pq.offer(new Tuple2 (t.x + 1, t.y, nums1[t.x + 1] + nums2[t.y]));// down direction
        }
        return res;
    }


class Tuple2 implements Comparable<Tuple2> {
    int x, y, val;
    public Tuple2 (int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo (Tuple2 that) {
        return this.val - that.val;
    }
}

    public static void main(String[] args) {

        FindKPairswithSmallestSums findKPairswithSmallestSums = new FindKPairswithSmallestSums();
      //  List<int[]> list = findKPairswithSmallestSums.kSmallestPairs(new int[]{1,7,11},new int[]{2,4,6}, 3);
       // List<int[]> list = findKPairswithSmallestSums.kSmallestPairs(new int[]{1,1,2},new int[]{1,2,3}, 2);
        List<int[]> list = findKPairswithSmallestSums.kSmallestPairs2(new int[]{1,2},new int[]{3}, 3);
        for (int[] t :
                list) {
            System.out.println(Arrays.toString(t));
        }
    }
}
