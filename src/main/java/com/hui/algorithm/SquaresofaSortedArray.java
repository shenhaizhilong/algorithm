package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/20 20:42
 *
 * 977. Squares of a Sorted Array
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Example 2:
 *
 * Input: [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 *
 *
 * Note:
 *
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A is sorted in non-decreasing order.
 *
 *
 *
 *  // find the min abs(A)'s index;
 *  left = minIdx -1;
 *  right = minIdx
 *  Math.abs(A[left]) <= Math.abs(A[right]) left--
 *  else right++
 *
 */
public class SquaresofaSortedArray {

    public int[] sortedSquares(int[] A) {

        int minIdx = 0;
        int N = A.length;
        int[] ans = new int[N];
        for(int i = 0; i < N; i++)
        {
            if( i == N -1 || Math.abs(A[i]) < Math.abs(A[i +1]))
            {
                minIdx = i;
                break;
            }
        }

        int left = minIdx -1;
        int right = minIdx;
        int idx = 0;
        while(idx < N)
        {
            if(left >= 0 &&(right == N || Math.abs(A[left]) <= Math.abs(A[right])) )
            {
                ans[idx++] = A[left]*A[left];
                left--;
                continue;
            }
            if(right < N)
            {
                ans[idx++] = A[right]*A[right];
                right++;
            }
        }

        return ans;
    }


    // every loop, chose one from left side or right side
    public int[] sortedSquares2(int[] A) {

        int N = A.length;
        int[] ans = new int[N];
        int left = 0;
        int right = N -1;
        int idx = N -1;
        while (idx >=0)
        {
            if(Math.abs(A[left]) > Math.abs(A[right]))
            {
                ans[idx--] = A[left]*A[left];
                left++;
            }else {
                ans[idx--] = A[right]*A[right];
                right--;
            }
        }

        return ans;

    }
    public static void main(String[] args) {
        SquaresofaSortedArray squaresofaSortedArray = new SquaresofaSortedArray();
        Matrix.print(squaresofaSortedArray.sortedSquares(new int[]{-4,-1,0,3,10}));
        Matrix.print(squaresofaSortedArray.sortedSquares(new int[]{-7,-3,2,3,11}));
        Matrix.print(squaresofaSortedArray.sortedSquares(new int[]{-1,1}));
        Matrix.print(squaresofaSortedArray.sortedSquares(new int[]{-2,1}));


        System.out.println("******************");
        Matrix.print(squaresofaSortedArray.sortedSquares2(new int[]{-4,-1,0,3,10}));
        Matrix.print(squaresofaSortedArray.sortedSquares2(new int[]{-7,-3,2,3,11}));
        Matrix.print(squaresofaSortedArray.sortedSquares2(new int[]{-1,1}));
        Matrix.print(squaresofaSortedArray.sortedSquares2(new int[]{-2,1}));
    }
}
