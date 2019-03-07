package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/28 10:59
 *
 * 把数组中的负数和正数分开并保持负数的相对顺序不变; time: O(n) ; space : O(1)
 *  Arrays:  [2,3, -1,4,-10,5,11,-9,0]
 *            |     |
 *            slow fast
 *
 *
 */
public class MoveArrays {

    public void move(int[] nums)
    {
        int fast = 0;
        int slow = 0;
        int N = nums.length;
        while (fast < N)
        {
            while (fast < N && nums[fast] > 0)fast++;
            if(slow != fast && fast < N && nums[fast] < 0)
            {
                swap(nums, slow, fast);
            }
            slow++;
            fast++;

        }

    }

    public void move2(int[] nums)
    {
        int left = -1;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] < 0 && left != i)
            {
                swap(nums, i, ++left);
            }
        }

    }

    private void swap(int[] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        MoveArrays moveArrays = new MoveArrays();
        int[] arr ={ 10, -2, 5, 8, -4, 2, -3, 7, 12, -88, -23, 35};
        moveArrays.move2(arr);
        Matrix.print(arr);

        Sort.bubleSort(arr);
        Matrix.print(arr);
    }
}
