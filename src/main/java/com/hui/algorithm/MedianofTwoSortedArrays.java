package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/27 16:09
 */
public class MedianofTwoSortedArrays {


    /**
     *
     * 4. Median of Two Sorted Arrays
     * DescriptionHintsSubmissionsDiscussSolution
     * There are two sorted arrays nums1 and nums2 of size m and n respectively.
     *
     * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
     *
     * You may assume nums1 and nums2 cannot be both empty.
     *
     * Example 1:
     *
     * nums1 = [1, 3]
     * nums2 = [2]
     *
     * The median is 2.0
     * Example 2:
     *
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     *
     * The median is (2 + 3)/2 = 2.5
     *
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] res = new int[nums1.length + nums2.length];
        merge(nums1, nums2, res );
        int m = res.length /2;
        if(res.length %2 == 0)
        {

            return 1.0D*(res[m] + res[m-1])/2;
        }
        return 1.0D*res[m];
    }

    private void merge(int[] nums1, int[] nums2, int[] res)
    {
        int position = -1;
        int left = 0;
        int right = 0;
        while (left < nums1.length && right < nums2.length)
        {
            if(nums1[left] <= nums2[right])
            {
                res[++position] = nums1[left++];
            }else {
                res[++position] = nums2[right++];
            }
        }

        while (left < nums1.length)
        {
            res[++position] = nums1[left++];
        }

        while (right < nums2.length)
        {
            res[++position] = nums2[right++];
        }
    }

    public static void main(String[] args) {
        MedianofTwoSortedArrays medianofTwoSortedArrays = new MedianofTwoSortedArrays();
        System.out.println(medianofTwoSortedArrays.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));

    }

}
