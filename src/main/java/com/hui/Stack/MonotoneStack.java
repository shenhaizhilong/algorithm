package com.hui.Stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/3 10:16
 *
 * https://leetcode.com/problems/sum-of-subarray-minimums/discuss/178876/stack-solution-with-very-detailed-explanation-step-by-step
 *
 *
 *Before diving into the solution, we first introduce a very important stack type, which is called monotone stack .
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
 */
public class MonotoneStack {


    // get increase stack's elements and index
    public Deque<int[]> increaseStack(int[] arr)
    {
        Deque<int[]> increase = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (!increase.isEmpty() && increase.peekFirst()[0] > arr[i])
            {
                increase.pop();
            }

            increase.push(new int[]{arr[i], i});
        }

        return increase;
    }

    /**
     *
     * it's non- strict less
     *
     * @param arr
     * @return
     */
    public int[] getPrevLess(int[] arr)
    {
        int N = arr.length;
        int[] prevLess = new int[N];
      //  Arrays.fill(prevLess, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty() && arr[stack.peekFirst().intValue()] > arr[i])
            {
                    stack.pop();
            }

            prevLess[i] = stack.isEmpty() ? -1: stack.peekFirst();
            stack.push(i);
        }

        return prevLess;

    }


    /**
     *
     * strict  prev less
     * @param arr
     * @return
     */
    public int[] getPrevLessStrict(int[] arr)
    {
        int N = arr.length;
        int[] prevLess = new int[N];
        prevLess[0] = -1;

        for (int i = 1; i < N; i++) {
            int p = i -1;
            while (p >= 0 && arr[p] >= arr[i])p = prevLess[p];
            prevLess[i] = p;

        }

        return prevLess;

    }


    public int[] getPrevLessNonStrict(int[] arr)
    {
        int N = arr.length;
        int[] prevLess = new int[N];
        prevLess[0] = -1;

        for (int i = 1; i < N; i++) {
            int p = i -1;
            while (p >= 0 && arr[p] > arr[i])p = prevLess[p];

            prevLess[i] = p;

        }

        return prevLess;

    }

    /**
     *
     * it's strict less
     * @param arr
     * @return
     */
    public int[] getNextLess(int[] arr)
    {
        int N = arr.length;
        int[] nextLess = new int[N];
        Arrays.fill(nextLess, N);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty() && arr[stack.peekFirst().intValue()] > arr[i])
            {
               int idx =  stack.pop();
               nextLess[idx] = i;
            }
            stack.push(i);
        }


        return nextLess;

    }

    public int[] getNextLessStrict(int[] arr)
    {
        int N = arr.length;
        int[] nextLess = new int[N];
        nextLess[N -1] = N;
        for (int i = N -2; i >=0; i--) {
            int p = i + 1;
            while (p < N && arr[p] >= arr[i])
            {
                p = nextLess[p];
            }

            nextLess[i] = p;
        }

        return nextLess;
    }




    /**
     *
     * // get decrease stack's elements and index
     *
     * @param arr
     * @return
     */
    public Deque<int[]> decreaseStack(int[] arr)
    {
        Deque<int[]> decrease = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (!decrease.isEmpty() && decrease.peekFirst()[0] < arr[i])
            {
                decrease.pop();
            }
            decrease.push(new int[]{arr[i], i});
        }

        return decrease;
    }


    /**
     *
     * it's non- strict big
     * @param arr
     * @return
     */
    public int[] getPrevBig(int[] arr)
    {
        int N = arr.length;
        int[] prevBig = new int[N];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty() && arr[stack.peekFirst().intValue()] < arr[i])
            {
                stack.pop();
            }

            prevBig[i] = stack.isEmpty() ? -1: stack.peekFirst();
            stack.push(i);
        }


        return prevBig;

    }

    public int[] getPrevBigStrict(int[] arr)
    {
        int N = arr.length;
        int[] prevBig = new int[N];
        prevBig[0] = -1;
        for (int i = 1; i < N; i++) {
            int p = i -1;
            while (p >= 0 && arr[p] <= arr[i])
            {
                p = prevBig[p];
            }
            prevBig[i] = p;
        }

        return prevBig;

    }


    /**
     *
     * get prev big len
     * for an example
     *
     * array      = [100, 80, 60, 70, 60, 75, 85]
     * prevBig    = [-1, 0,   1,   1, 3,   1,  0]
     * prevBiglen = [1, 1,    1,   2, 1,   4,  6], prevBigLen[i] = i - prevBig[i]
     * @param arr
     * @return
     */
    public int[] getPrevBigLen(int[] arr)
    {
        int N = arr.length;
        int[] prevBig = new int[N];
        int[] prevBigLen = new int[N];
        prevBigLen[0] = 1;
        prevBig[0] = -1;
        for (int i = 1; i < N; i++) {
            int p = i -1;
            while (p >= 0 && arr[p] <= arr[i])
            {
                p = prevBig[p];
            }
            prevBig[i] = p;
            prevBigLen[i] = i - p;
        }

        return prevBigLen;

    }



    /**
     *
     * it's strict big
     * @param arr
     * @return
     */
    public int[] getNextBig(int[] arr)
    {
        int N = arr.length;
        int[] nextBig = new int[N];
        Arrays.fill(nextBig, N);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty() && arr[stack.peekFirst().intValue()] < arr[i])
            {
               int idx =   stack.pop();
               nextBig[idx] = i;
            }
            stack.push(i);
        }


        return nextBig;

    }

    public int[] getNextBigStrict(int[] arr)
    {
        int N = arr.length;
        int[] nextBig = new int[N];
        nextBig[N -1] = N;
        for (int i = N-2; i >=0; i--) {
            int p = i + 1;
            while (p < N && arr[p] <= arr[i])
            {
                p = nextBig[p];
            }
            nextBig[i] = p;

        }

        return nextBig;

    }

    public int[] getNextBigLen(int[] arr)
    {
        int N = arr.length;
        int[] nextBig = new int[N];
        nextBig[N -1] = N;
        int[] nextBigLen = new int[N];
        nextBigLen[N-1] = 1;
        for (int i = N-2; i >=0; i--) {
            int p = i + 1;
            int r = 1;
            while (p < N && arr[p] <= arr[i])
            {
                r += nextBigLen[p];
                p = nextBig[p];
            }
            nextBig[i] = p;
            nextBigLen[i] = r;

        }

        return nextBigLen;

    }



    /**
     *
     * [2, 9, 7, 8, 3, 4, 6, 1]
     *
     * @param A
     * @return
     */
    public int sumSubarrayMax(int[] A) {
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

        Deque<Integer> prevBigStack = new ArrayDeque<>();
        Deque<Integer> nextBigStack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!prevBigStack.isEmpty() && A[prevBigStack.peekFirst().intValue()] < A[i])
            {
                prevBigStack.pop();
            }

            left[i] = prevBigStack.isEmpty() ? i + 1: i - prevBigStack.peekFirst(); // the number of elements less than A[i] in left side
            prevBigStack.push(i);

            while (!nextBigStack.isEmpty() && A[nextBigStack.peekFirst().intValue()] < A[i])
            {
                int idx = nextBigStack.pop();
                right[idx] = i - idx;// the number of elements bigger than A[i] in right side
            }
            nextBigStack.push(i);
        }



        for (int i = 0; i < N; i++) {
            ans = ( ans + A[i]*left[i]*right[i]) % Mod;
        }

        return (int) ans;
    }


    public static void main(String[] args) {

        MonotoneStack monotoneStack = new MonotoneStack();
       // System.out.println(monotoneStack.increaseStack(new int[]{3,7,8,4}));
      //  System.out.println(monotoneStack.decreaseStack(new int[]{3,7,8,4}));
        System.out.println(monotoneStack.decreaseStack(new int[]{9,8,7,6,5,3}));
        System.out.println(Arrays.toString(monotoneStack.getPrevLess(new int[]{3,7,7,4})));
        System.out.println(Arrays.toString(monotoneStack.getPrevLessNonStrict(new int[]{3,7,7,4})));
        System.out.println(Arrays.toString(monotoneStack.getPrevLessStrict(new int[]{3,7,7,4})));
        System.out.println(Arrays.toString(monotoneStack.getNextLess(new int[]{3,7,7,4})));
        System.out.println(Arrays.toString(monotoneStack.getNextLessStrict(new int[]{3,7,7,4})));

        System.out.println("*****************");
        System.out.println(Arrays.toString(monotoneStack.getPrevBig(new int[]{3,7,8,4})));
        System.out.println(Arrays.toString(monotoneStack.getPrevBig(new int[]{8,7,3,7,4,4})));
        System.out.println(Arrays.toString(monotoneStack.getNextBig(new int[]{8,7,3,7,10,4})));
        System.out.println("*****************");
        System.out.println(Arrays.toString(monotoneStack.getPrevLess(new int[]{2,1,5,6,4,5,2,3})));
        System.out.println(Arrays.toString(monotoneStack.getNextLess(new int[]{2,1,5,6,4,5,2,3})));

        System.out.println(monotoneStack.sumSubarrayMax(new int[]{3,7,8,4}));
        System.out.println("*****************");
        System.out.println(Arrays.toString(monotoneStack.getPrevBigStrict(new int[]{100, 80, 60, 70, 60, 75, 85})));
        System.out.println(Arrays.toString(monotoneStack.getPrevBigLen(new int[]{100, 80, 60, 70, 60, 75, 85})));
        System.out.println(Arrays.toString(monotoneStack.getNextBigStrict(new int[]{100, 80, 60, 70, 60, 75, 85})));
        System.out.println(Arrays.toString(monotoneStack.getNextBig(new int[]{100, 80, 60, 70, 60, 75, 85})));
        System.out.println(Arrays.toString(monotoneStack.getNextBigLen(new int[]{100, 80, 60, 70, 60, 75, 85})));


    }
}
