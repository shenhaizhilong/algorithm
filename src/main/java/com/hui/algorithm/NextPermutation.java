package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/9 18:31
 */
public class NextPermutation {


    /**
     *
     * 31. Next Permutation
     *
     * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
     *
     * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
     *
     * The replacement must be in-place and use only constant extra memory.
     *
     * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
     *
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     *
     *https://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order
     *
     * The following algorithm generates the next permutation lexicographically after a given permutation. It changes the given permutation in-place.
     *
     * Find the largest index k such that a[k] < a[k + 1]. If no such index exists, the permutation is the last permutation.
     * Find the largest index l greater than k such that a[k] < a[l].
     * Swap the value of a[k] with that of a[l].
     * Reverse the sequence from a[k + 1] up to and including the final element a[n].
     *
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length;

        // Find the largest index k such that a[k] < a[k + 1].
        int k = len -2;
        while (k >=0 && nums[k] >= nums[k +1])
        {
            k--;
        }

        // no such index exist
        if(k < 0)
        {
            reverse(nums,0, len -1);
            return;
        }

        // Find the largest index l greater than k such that a[k] < a[l].
        int largest = len -1;
        while (largest > k && nums[largest] <= nums[k])
        {
            largest--;
        }
        //Swap the value of a[k] with that of a[l]
        swap(nums,k, largest);
        //Reverse the sequence from a[k + 1] up to the end
        reverse(nums, k + 1, len-1);

    }

    public void swap(int[] nums, int i, int j)
    {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public void reverse(int[] nums, int start, int end)
    {
        while (start < end)
        {
            swap(nums,start,end);
            start++;
            end--;
        }
    }


    /**
     *
     * https://en.wikipedia.org/wiki/Heap%27s_algorithm
     *Heap's algorithm
     *  it generates each permutation from the previous one by interchanging a single pair of elements; the other n−2 elements are not disturbed.
     *  Robert Sedgewick concluded that it was at that time the most effective algorithm for generating permutations by computer.[2]
     *but it's not  lexicographic order.
     * @param nums
     */
    public void nextPermutation2(int[] nums) {
        int[] c = new int[nums.length];

        System.out.println(Arrays.toString(nums));

        int i = 0;
        while (i < nums.length)
        {
            if(c[i] < i)
            {
                if( (i &0x01) == 0)
                {
                    swap(nums,0,i);
                }else {
                    swap(nums, c[i],i);
                }
                System.out.println(Arrays.toString(nums));
                c[i] +=1;
                i = 0;

            }else {
                c[i] = 0;
                i += 1;
            }
        }
    }

    public static void main(String[] args) {


        NextPermutation nextPermutation = new NextPermutation();
        int[] nums = {1,2,3,4};
//        nextPermutation.nextPermutation(nums);
//        System.out.println(Arrays.toString(nums));
//        nextPermutation.nextPermutation(nums);
//        System.out.println(Arrays.toString(nums));
//
        nextPermutation.nextPermutation2(nums);

    }
}
