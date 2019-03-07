package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/20 10:45
 *
 *
 *
 * 923. 3Sum With Multiplicity
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an integer array A, and an integer target, return the number of tuples i, j, k  such that i < j < k and A[i] + A[j] + A[k] == target.
 *
 * As the answer can be very large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
 * Output: 20
 * Explanation:
 * Enumerating by the values (A[i], A[j], A[k]):
 * (1, 2, 5) occurs 8 times;
 * (1, 3, 4) occurs 8 times;
 * (2, 2, 4) occurs 2 times;
 * (2, 3, 3) occurs 2 times.
 * Example 2:
 *
 * Input: A = [1,1,2,2,2,2], target = 5
 * Output: 12
 * Explanation:
 * A[i] = 1, A[j] = A[k] = 2 occurs 12 times:
 * We choose one 1 from [1,1] in 2 ways,
 * and two 2s from [2,2,2,2] in 6 ways.
 *
 *
 * Note:
 *
 * 3 <= A.length <= 3000
 * 0 <= A[i] <= 100
 * 0 <= target <= 300
 *
 *
 *https://leetcode.com/problems/3sum-with-multiplicity/discuss/181131/C++JavaPython-O(1012)
 *
 * Count the occurrence of each number.
 * using hashmap or array up to you.
 *
 * Loop i on all numbers,
 * loop j on all numbers,
 * check if k = target - i - j is valid.
 *
 * Add the number of this combination to result.
 * 3 cases covers all possible combination:
 *
 * i == j == k
 * i == j != k
 * i < k && j < k
 * Time Complexity:
 * 3 <= A.length <= 3000, so N = 3000
 * But 0 <= A[i] <= 100
 * So my solution is O(101^2)
 *
 */
public class Sum3WithMultiplicity {

    public int threeSumMulti(int[] A, int target) {
        long[] counter = new long[101];
        for(int n:A)counter[n]++;
        long ans = 0;
        for (int i = 0; i < 101; i++) {
            for (int j = i; j <101 ; j++) {
                int k = target - i - j;
                if(k > 100)continue;
                if(k < 0)break;
                if(i == j && j == k)
                {
                    ans += (counter[i] -1)*(counter[i] -2)*counter[i]/6;
                }else if(i == j && j != k)
                {
                    ans += counter[i]*(counter[i] -1)/2*counter[k];
                }else if(j < k) {
                    ans += counter[i]*counter[j]*counter[k];
                }

            }
        }
        return (int)(ans %(1e9 +7));
    }

    public static void main(String[] args) {

        Sum3WithMultiplicity sum3WithMultiplicity = new Sum3WithMultiplicity();
        System.out.println(sum3WithMultiplicity.threeSumMulti(new int[]{2,2,4}, 8));
    }
}
