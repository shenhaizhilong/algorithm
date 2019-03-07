package com.hui.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/9 21:42
 */
public class PermutationSequence {


    /**
     *60. Permutation Sequence
     * DescriptionHintsSubmissionsDiscussSolution
     * The set [1,2,3,...,n] contains a total of n! unique permutations.
     *
     * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
     *
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * Given n and k, return the kth permutation sequence.
     *
     * Note:
     *
     * Given n will be between 1 and 9 inclusive.
     * Given k will be between 1 and n! inclusive.
     * Example 1:
     *
     * Input: n = 3, k = 3
     * Output: "213"
     * Example 2:
     *
     * Input: n = 4, k = 9
     * Output: "2314"
     *
     *
     *
     * example:
     * n = 4, k = 14:
     *
     * We have {1, 2, 3, 4}, find the 14th permutation.
     * List out all the permutations:
     *
     * 1 + (permutations of 2, 3, 4)
     * 2 + (permutations of 1, 3, 4)
     * 3 + (permutations of 1, 2, 4)
     * 4 + (permutations of 1, 2, 3)
     *
     * To find the 14th, we can see it falling to range 3 + (permutations of 1, 2, 4), since 1 + (permutations of 2, 3, 4) and 2 + (permutations of 1, 3, 4) could take the first 2 * (3!) = 12 permutations. So we can know the first number of result is 3.
     *
     * Then the question turn to be a smaller problem.
     * {1, 2, 4}, find the 2nd permutation.
     * List out all the permutations:
     * 1 + (permutations of 2, 4)
     * 2 + (permutations of 1, 4)
     * 4 + (permutations of 1, 2)
     *
     * There are 2! + 2! + 2!, 6 permutation. The 2nd must be in range 1 + (permutations of 2, 4). So we can know the second number of result is 1.
     *
     * So the question turn be a smaller problem.
     * {2, 4}, find the 2nd permutation. The answer is (4, 2).
     * So the final result is (3, 1, 4, 2)
     *
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {

        List<Integer> list = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int[] dp = new int[n];
        dp[0] = 1;
        // compute the factorial of 1 to n-1
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i-1]*i;
            list.add(i);
        }
        list.add(n);

        k--;
        while (n > 0)
        {
            int index = k / dp[n-1];   // group index, index start from 0
            // get number based on the group index
            sb.append(list.get(index));
            list.remove(list.get(index));
            k = k % dp[n-1];//  k = groupIndex*f(n-1) + offset, k' = offset
            n = n -1;
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        PermutationSequence sequence = new PermutationSequence();
        System.out.println(sequence.getPermutation(4,14));
    }
}
