package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/21 0:16
 *
 * 荷兰国旗问题
 * 给定一个数组arr，和一个数num，请把小于num的数放在数组的左边，等于num的数放在数组的中间，大于num的数放在数组的右边。
 *
 * {1,2, 1, 5, 4, 7, 2, 3, 9,1}
 *
 *
 */
public class SplitNumbers {
    public void partition(int[] arr,int startIdx,int endIdx, int num)
    {
        int left = startIdx -1;
        int right = endIdx +1;
        int idx = startIdx;
        while (idx < right)
        {
            if(arr[idx] < num)
            {
                swap(arr, idx++, ++left);
            }else if(arr[idx] > num)
            {
                swap(arr, idx, --right);
            }else {
                idx++;
            }
        }

    }

    private void swap(int[] arr, int i, int j)
    {
        if(i == j)return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        SplitNumbers splitNumbers = new SplitNumbers();
        int[] nums = new int[]{7,7,1,8,12,2,1,8,9, 5, 4, 7, 2, 3, 9,1};
        splitNumbers.partition(nums,0, nums.length -1, 7);
        Matrix.print(nums);
    }
}
