package com.hui.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/12 17:44
 *
 * 658. Find K Closest Elements
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 *
 * Example 1:
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 * Example 2:
 * Input: [1,2,3,4,5], k=4, x=-1
 * Output: [1,2,3,4]
 * Note:
 * The value k is positive and will always be smaller than the length of the sorted array.
 * Length of the given array is positive and will not exceed 104
 * Absolute value of elements in the array and x will not exceed 104
 * UPDATE (2017/9/19):
 * The arr parameter had been changed to an array of integers (instead of a list of integers). Please reload the code definition to get the latest changes.
 */
public class FindKClosestElements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int idx = Arrays.binarySearch(arr,x);
        List<Integer> res = new ArrayList<>(k);
        if(idx <0)idx = -idx -1;
        int left = idx;
        int right = left;
        int Len = arr.length;
        while (right - left < k)
        {

            // left edge
            if(left == 0)
            {
                right = k;
                break;
            }

            // right edge
            if(right == Len)
            {
                left = right -k;
                break;
            }


            // expand from the center.
            // if we can expand to left -1, then do it, else expand to right
            if(x - arr[left -1] <= arr[right] -x)left--;
            else right++;

        }

        for (int i = left; i < right; i++) {
            res.add(arr[i]);
        }

        return res;

    }

    /**
     *
     *  x - a[left] <= a[right] - x;
     *  right = left + k; then x - a[left] <= a[left + k] -x ;then 2*x <= a[left] + a[left +k], then x<= (a[left] + a[left + k])/2 , find the min left
     *
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;
        while (left < right)
        {
            int mid = (left + right)>>>1;
           if(2*x <= arr[mid] + arr[mid + k]) right = mid;
           else left = mid +1;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    public static void main(String[] args) {

        FindKClosestElements findKClosestElements = new FindKClosestElements();
//        System.out.println(findKClosestElements.findClosestElements(new int[]{1,2,3,4,5},4,3));
//        System.out.println(findKClosestElements.findClosestElements(new int[]{1,2,3,4,5},4,4));
//        System.out.println(findKClosestElements.findClosestElements(new int[]{1,2,3,4,5},4,5));
//        System.out.println(findKClosestElements.findClosestElements(new int[]{1,2,3,4,5},4,-1));
//        System.out.println(findKClosestElements.findClosestElements(new int[]{1},1,-1));

        System.out.println("*******************");
        System.out.println(findKClosestElements.findClosestElements2(new int[]{1,2,3,4,5},2,3));
//        System.out.println(findKClosestElements.findClosestElements2(new int[]{1,2,3,4,5},4,4));
//        System.out.println(findKClosestElements.findClosestElements2(new int[]{1,2,3,4,5},4,5));
//        System.out.println(findKClosestElements.findClosestElements2(new int[]{1,2,3,4,5},4,-1));
//        System.out.println(findKClosestElements.findClosestElements2(new int[]{1},1,-1));
    }

}
