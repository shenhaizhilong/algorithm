package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/15 10:41
 */
public class CreateMaximumNumber {


    /**
     *321. Create Maximum Number
     * DescriptionHintsSubmissionsDiscussSolution
     * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits.
     *
     * Note: You should try to optimize your time and space complexity.
     *
     * Example 1:
     *
     * Input:
     * nums1 = [3, 4, 6, 5]
     * nums2 = [9, 1, 2, 5, 8, 3]
     * k = 5
     * Output:
     * [9, 8, 6, 5, 3]
     * Example 2:
     *
     * Input:
     * nums1 = [6, 7]
     * nums2 = [6, 0, 4]
     * k = 5
     * Output:
     * [6, 7, 6, 0, 4]
     * Example 3:
     *
     * Input:
     * nums1 = [3, 9]
     * nums2 = [8, 9]
     * k = 3
     * Output:
     * [9, 8, 9]
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        int N = nums1.length;
        int M = nums2.length;
        int[] ans = new int[k];
        // since k maybe bigger than M, so max k' in nums2 is k - (k -m)
        for (int i = Math.max(0, k - M); i <= k && i <= N; i++)
        {
            int[] curr = merge(findMaxSubArray(nums1, i), findMaxSubArray(nums2, k - i));
            if(larger(curr, 0, ans, 0))
            {
                ans = curr;
            }

        }
        return ans;


    }

    // stack: 0 1 2 3 4 5
    //            |
    //            idx
    // greedy method to find the max sub array
    private int[] findMaxSubArray(int[] nums, int k)
    {
        int N = nums.length;
        int[] stack = new int[k];
        int idx = 0;  // -1 means the stack is empty
        for (int i = 0; i < N; i++) {
            while (idx > 0 && stack[idx -1] < nums[i] && N - (1 +i) + idx >= k)
            {
                idx--;
            }
            if(idx < k)
            {
                stack[idx++] = nums[i];
            }
        }

        return stack;

    }

    // check whether arr1[x...end] is larger than arr2[y...end]
    private boolean larger(int[] arr1, int x, int[] arr2, int y)
    {
        int M = arr1.length;
        int N = arr2.length;
        while (x < M && y < N && arr1[x] == arr2[y])
        {
            x++;
            y++;
        }
        // arr1 is larger than arr2 , or arr1 == arr2
        if(y == N)return true;
        return x < M && arr1[x] > arr2[y];
    }

    private int[] merge(int[] arr1, int[] arr2)
    {
        int M = arr1.length;
        int N = arr2.length;
        int[] ans = new int[M +N];
        int left = 0;
        int right = 0;
        int idx = -1;
        while (left < M && right < N)
        {
            ans[++idx] = larger(arr1, left, arr2, right) ? arr1[left++]:arr2[right++];
        }
        while (left < M)
        {
            ans[++idx] = arr1[left++];
        }
        while (right < N)
        {
            ans[++idx] = arr2[right++];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 4, 6, 5};
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        int k = 5;
        CreateMaximumNumber createMaximumNumber = new CreateMaximumNumber();
        Matrix.print(createMaximumNumber.maxNumber(nums1,nums2,k));

    }
}
