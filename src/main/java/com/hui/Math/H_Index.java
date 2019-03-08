package com.hui.Math;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/16 14:52
 */
public class H_Index {


    /**
     *274. H-Index
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
     *
     * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
     *
     * Example:
     *
     * Input: citations = [3,0,6,1,5]
     * Output: 3
     * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
     *              received 3, 0, 6, 1, 5 citations respectively.
     *              Since the researcher has 3 papers with at least 3 citations each and the remaining
     *              two with no more than 3 citations each, her h-index is 3.
     * Note: If there are several possible values for h, the maximum one is taken as the h-index.
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {


        /**
         * https://en.wikipedia.org/wiki/H-index
         * If we have the function f ordered in decreasing order from the largest value to the lowest one, we can compute the h index as follows:
         *
         * h-index (f) = max (min(f(i),i)) i = 1,2,3,4,5....len(f)
         */
        Arrays.sort(citations);
        int max = 0;
        int len = citations.length;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, Math.min(len - i, citations[i]));
        }
        return max;
    }

    public int hIndex2(int[] citations) {
        int[] bucket = new int[citations.length + 1];
        for (int i = 0; i < citations.length; i++) {
            if(citations[i] >= citations.length)
            {
                //// 引用数为大于等于citations.length 的文章有多少篇
                bucket[citations.length]++;
            }else {
                // 引用数为citations[i] 的文章有多少篇
                bucket[citations[i]]++;
            }
        }

        // A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each
        // find the max h_index.
        int count = 0;
        for (int i = bucket.length -1; i >=0 ; i--) {
            count += bucket[i];
            if(count >= i)
                return i;

        }

        return 0;
    }
    public static void main(String[] args) {
        H_Index h_index = new H_Index();
        System.out.println(h_index.hIndex(new int[]{3,0,10,1,5}));
        System.out.println(h_index.hIndex2(new int[]{3,0,10,1,5}));
    }
}
