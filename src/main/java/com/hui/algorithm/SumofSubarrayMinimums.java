package com.hui.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/31 11:04
 *907. Sum of Subarray Minimums
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
 *
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: [3,1,2,4]
 * Output: 17
 * Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 30000
 * 1 <= A[i] <= 30000
 *
 */
public class SumofSubarrayMinimums {

    public int sumSubarrayMins(int[] A) {
        if(A == null || A.length == 0)return 0;
        int N = A.length;
        long Mod = 1_000_000_007;
        long ans = 0;
        for (int i = 0; i < N; i++) {
            int min =  A[i];
            ans = (ans + min) % Mod;
            for (int j = i -1; j >=0 ; j--) {
                min = min > A[j] ? A[j]: min;
                ans = (ans + min) % Mod;
            }
        }

        return (int)ans;
    }


    /**
     *
     *
     * https://leetcode.com/problems/sum-of-subarray-minimums/discuss/178876/stack-solution-with-very-detailed-explanation-step-by-step
     *
     *
     * Before diving into the solution, we first introduce a very important stack type, which is called monotone stack .
     *
     * What is monotonous increase stack?
     * Roughly speaking, the elements in the an monotonous increase stack keeps an increasing order.
     *
     * The typical paradigm for monotonous increase stack:
     * for(int i = 0; i < A.size(); i++){
     *   while(!in_stk.empty() && in_stk.top() > A[i]){
     *     in_stk.pop();
     *   }
     *   in_stk.push(A[i]);
     * }
     * What can monotonous increase stack do?
     * (1) find the previous less element of each element in a vector with O(n) time:
     * What is the previous less element of an element?
     * For example:
     * [3, 7, 8, 4]
     * The previous less element of 7 is 3.
     * The previous less element of 8 is 7.
     * The previous less element of 4 is 3.
     * There is no previous less element for 3.
     * For simplicity of notation, we use abbreviation PLE to denote Previous Less Element.
     *
     * C++ code (by slitghly modifying the paradigm):
     * Instead of directly pushing the element itself, here for simplicity, we push the index.
     * We do some record when the index is pushed into the stack.
     * // previous_less[i] = j means A[j] is the previous less element of A[i].
     * // previous_less[i] = -1 means there is no previous less element of A[i].
     * vector<int> previous_less(A.size(), -1);
     * for(int i = 0; i < A.size(); i++){
     *   while(!in_stk.empty() && A[in_stk.top()] > A[i]){
     *     in_stk.pop();
     *   }
     *   previous_less[i] = in_stk.empty()? -1: in_stk.top();
     *   in_stk.push(i);
     * }
     * (2) find the next less element of each element in a vector with O(n) time:
     * What is the next less element of an element?
     * For example:
     * [3, 7, 8, 4]
     * The next less element of 8 is 4.
     * The next less element of 7 is 4.
     * There is no next less element for 3 and 4.
     * For simplicity of notation, we use abbreviation NLE to denote Next Less Element.
     * C++ code (by slitgtly modifying the paradigm):
     * We do some record when the index is poped out from the stack.
     * // next_less[i] = j means A[j] is the next less element of A[i].
     * // next_less[i] = -1 means there is no next less element of A[i].
     * vector<int> previous_less(A.size(), -1);
     * for(int i = 0; i < A.size(); i++){
     *   while(!in_stk.empty() && A[in_stk.top()] > A[i]){
     *     auto x = in_stk.top(); in_stk.pop();
     *     next_less[x] = i;
     *   }
     *   in_stk.push(i);
     * }
     * How can the monotonous increase stack be applied to this problem?
     * For example:
     * Consider the element 3 in the following vector:
     *
     *                             [2, 9, 7, 8, 3, 4, 6, 1]
     * 			     |                    |
     * 	             the previous less       the next less
     * 	                element of 3          element of 3
     *
     * After finding both NLE and PLE of 3, we can determine the
     * distance between 3 and 2(previous less) , and the distance between 3 and 1(next less).
     * In this example, the distance is 4 and 3 respectively.
     *
     * How many subarrays with 3 being its minimum value?
     * The answer is 4*3.
     *
     * 9 7 8 3
     * 9 7 8 3 4
     * 9 7 8 3 4 6
     * 7 8 3
     * 7 8 3 4
     * 7 8 3 4 6
     * 8 3
     * 8 3 4
     * 8 3 4 6
     * 3
     * 3 4
     * 3 4 6
     * How much the element 3 contributes to the final answer?
     * It is 3*(4*3).
     * What is the final answer?
     * Denote by left[i] the distance between element A[i] and its PLE.
     * Denote by right[i] the distance between element A[i] and its NLE.
     *
     * The final answer is,
     * sum(A[i]*left[i]*right[i] )
     *
     *
     * @param A
     * @return
     */

    public int sumSubarrayMins2(int[] A) {
        if (A == null || A.length == 0) return 0;
        int N = A.length;
        long Mod = 1_000_000_007;
        long ans = 0;
        int[] left = new int[N];
        int[] right = new int[N];
        for (int i = 0; i < N; i++) {
            left[i] = i + 1; // the default number of elements from index 0 to index i, including index i;
            right[i] = N - i;  // the default number of elements from index N -1 to index i, including index i;
        }

        Deque<int[]> prevLessStack = new ArrayDeque<>();
        Deque<int[]> nextLessStack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!prevLessStack.isEmpty() && prevLessStack.peekFirst()[0] > A[i]) prevLessStack.pop();
            left[i] = prevLessStack.isEmpty() ? i + 1: i - prevLessStack.peekFirst()[1];
            prevLessStack.push(new int[]{A[i], i});

            while (!nextLessStack.isEmpty() && nextLessStack.peekFirst()[0] > A[i])
            {
                int[] x = nextLessStack.pop();
                right[x[1]] = i - x[1];
            }

            nextLessStack.push(new int[]{A[i], i});
        }

        for (int i = 0; i < N; i++) {
            ans = ( ans + A[i]*left[i]*right[i]) % Mod;
        }

        return (int) ans;


    }

    public static void main(String[] args) {

        SumofSubarrayMinimums sumofSubarrayMinimums = new SumofSubarrayMinimums();
        System.out.println(sumofSubarrayMinimums.sumSubarrayMins(new int[]{3,1,2,4}));
        System.out.println(sumofSubarrayMinimums.sumSubarrayMins(new int[]{1,3,2,4}));

        System.out.println(sumofSubarrayMinimums.sumSubarrayMins2(new int[]{3,1,2,4}));
        System.out.println(sumofSubarrayMinimums.sumSubarrayMins2(new int[]{1,3,2,4}));

    }
}
