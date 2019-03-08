package com.hui.Array;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/31 23:27
 *
 *
 * 845. Longest Mountain in Array
 * DescriptionHintsSubmissionsDiscussSolution
 * Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
 *
 * B.length >= 3
 * There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * (Note that B could be any subarray of A, including the entire array A.)
 *
 * Given an array A of integers, return the length of the longest mountain.
 *
 * Return 0 if there is no mountain.
 *
 * Example 1:
 *
 * Input: [2,1,4,7,3,2,5]
 * Output: 5
 * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 * Example 2:
 *
 * Input: [2,2,2]
 * Output: 0
 * Explanation: There is no mountain.
 * Note:
 *
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * Follow up:
 *
 * Can you solve it using only one pass?
 * Can you solve it in O(1) space?
 *
 */
public class LongestMountaininArray {



    public int longestMountain(int[] A) {
        if(A == null || A.length < 3)return 0;
        int ans = 0;
        for (int i = 1; i < A.length -1 ; i++) {
            int peakIndex  = i;
            if(A[peakIndex] <= A[peakIndex -1])continue;
            while (peakIndex < A.length -1 &&  A[peakIndex] <= A[peakIndex +1])
            {
                peakIndex++;
            }
            if(peakIndex == A.length -1)break;
            int left = peakIndex - 1;
            // find left bound
            while (left >=0  && A[left] < A[left + 1])left--;
            if(left == peakIndex -1)continue; // didn't have left part
            int right = peakIndex + 1;
            // find right bound
            while (right < A.length && A[right -1] > A[right])right++;
            ans = Math.max(ans, right - left - 1);
            // move to bound
            i = right -1;
        }

        return  ans;

    }


    public int longestMountain2(int[] A) {
        if(A == null || A.length < 3)return 0;
        int ans = 0;
        for (int i = 1; i < A.length -1 ; i++) {
            int peakIndex  = i;
            if(A[peakIndex] <= A[peakIndex -1] || A[peakIndex] <= A[peakIndex + 1])continue;
            int left = peakIndex - 1;
            // find left bound
            while (left >=0  && A[left] < A[left + 1])left--;
            int right = peakIndex + 1;
            // find right bound
            while (right < A.length && A[right -1] > A[right])right++;
            ans = Math.max(ans, right - left - 1);
            // move to bound
            i = right -1;
        }

        return  ans;

    }

    public int longestMountain3(int[] A) {
        if (A == null || A.length < 3) return 0;
        int N = A.length;
        int left = 0;

        int ans = 0;
        while (left < N -1)
        {
            int right = left;
            if(right < N -1 && A[right] < A[right + 1])
            {
                // find the potential peak index, right
                while (right < N -1 && A[right] < A[right + 1])right++;
                // if right is really the peak index
                if( right < N -1 && A[right] > A[right + 1])
                {
                    // find the right bound
                    while (right < N -1 && A[right] > A[right +1])right++;
                    ans = Math.max(ans, right - left + 1);
                }

            }

            left = Math.max(left + 1, right);
        }

        return ans;
    }


    public int longestMountain4(int[] A) {
        if (A == null || A.length < 3) return 0;
        int N = A.length;

        int[] up = new int[N];   // 递增序列的元素到波峰左边波谷的距离
        int[] down = new int[N];  //递减序列的元素到波峰右边波谷的距离
        int ans = 0;

        // for example [3,2,1]
        // down = [2,1,0]
        for (int i = N -2; i >= 0; i--) {
            if(A[i] > A[i + 1])down[i] = down[i + 1] + 1;
        }

        for (int i = 0; i < N; i++) {
            if( i > 0 && A[i] > A[i -1]) up[i] = up[i -1] + 1;
            if(up[i] > 0 && down[i] > 0)
            {
                ans = Math.max(ans, down[i] + up[i] + 1);
            }
        }

        return ans;
    }

    public int longestMountain5(int[] A) {
        if (A == null || A.length < 3) return 0;
        int N = A.length;
        int up = 0;
        int down = 0;
        int ans = 0;
        for (int i = 1; i < N; i++) {
            if(down > 0 && A[i] > A[i -1] || A[i] == A[i -1]){
                up = 0;
                down = 0;
            }

            if(A[i] > A[i -1])up++;
            if(A[i] < A[i -1])down++;
            if(up > 0 && down > 0 && (up + down + 1 > ans))ans = up + down + 1;

        }

        return ans;

    }


        public static void main(String[] args) {

        LongestMountaininArray longestMountaininArray = new LongestMountaininArray();
        System.out.println(longestMountaininArray.longestMountain(new int[]{2,1,4,7,3,2,1}));
        System.out.println(longestMountaininArray.longestMountain(new int[]{2,2,2}));
        System.out.println(longestMountaininArray.longestMountain(new int[]{2,3,3,2,0,2}));

        System.out.println(longestMountaininArray.longestMountain2(new int[]{2,1,4,7,3,2,1}));
        System.out.println(longestMountaininArray.longestMountain2(new int[]{2,2,2}));
        System.out.println(longestMountaininArray.longestMountain2(new int[]{2,3,3,2,0,2}));

        System.out.println(longestMountaininArray.longestMountain3(new int[]{2,1,4,7,3,2,1}));
        System.out.println(longestMountaininArray.longestMountain3(new int[]{2,2,2}));
        System.out.println(longestMountaininArray.longestMountain3(new int[]{2,3,3,2,0,2}));

            System.out.println(longestMountaininArray.longestMountain4(new int[]{2,1,4,7,3,2,1}));
            System.out.println(longestMountaininArray.longestMountain4(new int[]{2,2,2}));
            System.out.println(longestMountaininArray.longestMountain4(new int[]{2,3,3,2,0,2}));

            System.out.println(longestMountaininArray.longestMountain5(new int[]{2,1,4,7,3,2,1}));
            System.out.println(longestMountaininArray.longestMountain5(new int[]{2,2,2}));
            System.out.println(longestMountaininArray.longestMountain5(new int[]{2,3,3,2,0,2}));

    }
}
