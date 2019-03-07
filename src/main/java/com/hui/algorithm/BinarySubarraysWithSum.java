package com.hui.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/29 19:01
 *930. Binary Subarrays With Sum
 * DescriptionHintsSubmissionsDiscussSolution
 * In an array A of 0s and 1s, how many non-empty subarrays have sum S?
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1,0,1,0,1], S = 2
 * Output: 4
 * Explanation:
 * The 4 subarrays are bolded below:
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 *
 *
 * Note:
 *
 * A.length <= 30000
 * 0 <= S <= A.length
 * A[i] is either 0 or 1.
 *
 */
public class BinarySubarraysWithSum {

    public int numSubarraysWithSum1(int[] A, int S) {
        if( A == null || S > A.length || S < 0)return 0;
        int len = A.length;
        int count = 0;
        int[] counter = new int[len + 1];
        int[] preSum = new int[len +1];
        for (int i = 0; i < len; i++) {
            preSum[i +1] = preSum[i] + A[i];

        }
        for(int p: preSum)
        {
            count += counter[p];
            int id = p + S;
            if(id <= len)
            {
                counter[id]++;
            }

        }


        return count;
    }

    /**
     *
     * 1. {1,0,1, , , } start = 0,end = 2 with sum from 0 to end is 2. Bingo!
     * 2  {,,1,1,,} start = 2,end =3 with
     *
     * sum from 0 to end: sum(end) = 1+0+1+1 = 3;
     * sum from 0 to start: sum(start) 1;
     * sum(end) = sum(start) + 3-1. find it.
     * Therefore, we need to check if: sum == S, then ans++;
     * Also, we need to see frequency f with index i which has relationship: sum  = S + i. Then ans = ans+f;
     *
     * @param A
     * @param S
     * @return
     */
    public int numSubarraysWithSum2(int[] A, int S) {
        if( A == null || S > A.length || S < 0)return 0;
        int len = A.length;
        int count = 0;
        int[] counter = new int[len + 1];
        int preSum = 0;
        for (int i = 0; i < len; i++) {
            preSum += A[i];
            count += counter[preSum];
            int id = preSum + S;
            if(preSum == S)count++;
            if(id <= len)
            {
                counter[id]++;
            }

        }


        return count;
    }


    /**
     *
     * Let P[i] = A[0] + A[1] + ... + A[i-1]. Then P[j+1] - P[i] = A[i] + A[i+1] + ... + A[j], the sum of the subarray [i, j].
     *
     * Hence, we are looking for the number of i < j with P[j] - P[i] = S
     *
     * @param A
     * @param S
     * @return
     */
    public int numSubarraysWithSum3(int[] A, int S) {
        if( A == null || S > A.length || S < 0)return 0;
        // prefix sum
        int len = A.length;
        int ans = 0;
        int PreSum = 0;
        Map<Integer, Integer> counter = new HashMap<>();
        for (int i = 0; i < len; i++) {
            PreSum += A[i];
            if(PreSum == S)ans++;
            ans += counter.getOrDefault(PreSum, 0);
            counter.put(PreSum + S, counter.getOrDefault(PreSum + S, 0) + 1);
        }


        return ans;
    }

        public static void main(String[] args) {
        BinarySubarraysWithSum binarySubarraysWithSum = new BinarySubarraysWithSum();

        System.out.println(binarySubarraysWithSum.numSubarraysWithSum1(new int[]{1,0,1,0,1}, 2));
        System.out.println(binarySubarraysWithSum.numSubarraysWithSum1(new int[]{1,0,1,0,1}, 1));

        System.out.println(binarySubarraysWithSum.numSubarraysWithSum2(new int[]{1,0,1,0,1}, 2));
        System.out.println(binarySubarraysWithSum.numSubarraysWithSum2(new int[]{1,0,1,0,1}, 1));
        System.out.println(binarySubarraysWithSum.numSubarraysWithSum3(new int[]{1,0,1,0,1}, 2));
        System.out.println(binarySubarraysWithSum.numSubarraysWithSum3(new int[]{1,0,1,0,1}, 1));


    }
}
