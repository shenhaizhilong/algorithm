package com.hui.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/7 23:29
 *
 *898. Bitwise ORs of Subarrays
 *
 * We have an array A of non-negative integers.
 *
 * For every (contiguous) subarray B = [A[i], A[i+1], ..., A[j]] (with i <= j), we take the bitwise OR of all the elements in B, obtaining a result A[i] | A[i+1] | ... | A[j].
 *
 * Return the number of possible results.  (Results that occur more than once are only counted once in the final answer.)
 *
 *
 *
 * Example 1:
 *
 * Input: [0]
 * Output: 1
 * Explanation:
 * There is only one possible result: 0.
 * Example 2:
 *
 * Input: [1,1,2]
 * Output: 3
 * Explanation:
 * The possible subarrays are [1], [1], [2], [1, 1], [1, 2], [1, 1, 2].
 * These yield the results 1, 1, 2, 1, 3, 3.
 * There are 3 unique values, so the answer is 3.
 * Example 3:
 *
 * Input: [1,2,4]
 * Output: 6
 * Explanation:
 * The possible results are 1, 2, 3, 4, 6, and 7.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 50000
 * 0 <= A[i] <= 10^9
 *
 */
public class BitwiseORsofSubarrays {


    public int subarrayBitwiseORs3(int[] A) {

        Set<Integer> ans = new HashSet<>();
        int[] memo = new int[A.length];
        int pre = -1;
        for (int i = 0; i < A.length; i++) {
            if(A[i] == pre)
            {
                memo[i] = A[i];
                continue;
            }
            pre = A[i];
            if(A[i] == 0){
                ans.add(0);
            }else {
                for (int j = i; j >= 0; j--) {
                    int v = A[i] | memo[j];
                    if(v == memo[j])break;
                    else {
                        memo[j] = v;
                        ans.add(v);
                    }
                }
            }


        }

        return ans.size();
    }


    public int subarrayBitwiseORs2(int[] A) {

        Set<Integer> ans = new HashSet<>();
        int[] memo = new int[A.length];
        int pre = -1;
        for (int i = 0; i < A.length; i++) {
            if(A[i] == pre)
            {
                memo[i] = A[i];
                continue;
            }
            pre = A[i];
            if(A[i] == 0){
                ans.add(0);
                continue;
            }

            for (int j = i; j >= 0; j--) {
                int v = A[i] | memo[j];
                if(v == memo[j])break;
                else {
                    memo[j] = v;
                    ans.add(v);
                }
            }
        }

        return ans.size();
    }

    public int subarrayBitwiseORs(int[] A) {

        Set<Integer> ans = new HashSet<>();
        Set<Integer> pre = new HashSet<>();
        Set<Integer> curr = null;
        int last = -1;
        for(int a:A)
        {
             if(last == a)continue;
             last = a;
            curr = new HashSet<>();
            curr.add(a);
            for(int b: pre)
            {
                curr.add(a | b);
            }
            pre = curr;
            ans.addAll(pre);

        }

        return ans.size();
    }

    public static void main(String[] args) {
        BitwiseORsofSubarrays bitwiseORsofSubarrays = new BitwiseORsofSubarrays();
        System.out.println(bitwiseORsofSubarrays.subarrayBitwiseORs(new int[]{1}));
        System.out.println(bitwiseORsofSubarrays.subarrayBitwiseORs(new int[]{1,1,2}));
        System.out.println(bitwiseORsofSubarrays.subarrayBitwiseORs(new int[]{1,2,4}));
        System.out.println(bitwiseORsofSubarrays.subarrayBitwiseORs(new int[]{15,54,12,68,33,127,82,115,14}));

        System.out.println("***************");

        System.out.println(bitwiseORsofSubarrays.subarrayBitwiseORs2(new int[]{1}));
        System.out.println(bitwiseORsofSubarrays.subarrayBitwiseORs2(new int[]{1,1,2}));
        System.out.println(bitwiseORsofSubarrays.subarrayBitwiseORs2(new int[]{1,2,4}));
        System.out.println(bitwiseORsofSubarrays.subarrayBitwiseORs2(new int[]{15,54,12,68,33,127,82,115,14}));

    }
}
