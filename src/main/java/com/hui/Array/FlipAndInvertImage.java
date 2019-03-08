package com.hui.Array;

import java.util.Arrays;

/**
 *
 * https://leetcode-cn.com/problems/flipping-an-image/description/
 * 832. 翻转图像
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 *
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 *
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 *
 * 示例 1:
 *
 * 输入: [[1,1,0],[1,0,1],[0,0,0]]
 * 输出: [[1,0,0],[0,1,0],[1,1,1]]
 * 解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 *      然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 * 示例 2:
 *
 * 输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * 输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 *      然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 说明:
 *
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j] <= 1
 *
 * @author: shenhaizhilong
 * @date: 2018/8/16 16:55
 */
public class FlipAndInvertImage {
    public int[][] flipAndInvertImage(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            flip(A[i]);
            invert(A[i]);
        }
        return A;

    }

    private void invert(int[] arr)
    {
        for (int i = 0; i < arr.length; i++) {
            arr[i] ^= 1;
        }
    }

    private void flip(int[] arr)
    {
        for (int i = 0; i < arr.length/2; i++) {
            swap(arr,i,arr.length -1 -i);
        }
    }
    private void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     *  解法二：
     * @param A
     * @return
     */
    public static int[][] flipAndInvertImage2(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            A[i] = f(A[i]);
        }
        return A;
    }

    public static int[] f(int[] a) {
        int l = a.length;
        int[] res = new int[l];
        for (int i = 0; i < l; i++) {
            res[i] = (a[l-i-1] + 1) &0x01;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = {{1,1,0,0},{1,0,0,1},{0,1,1,1},{1,0,1,0}};
        FlipAndInvertImage flipAndInvertImage = new FlipAndInvertImage();
        flipAndInvertImage.flipAndInvertImage(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }

        int[][] arr2 = {{1,1,0,0},{1,0,0,1},{0,1,1,1},{1,0,1,0}};
        flipAndInvertImage2(arr2);
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(Arrays.toString(arr2[i]));
        }
    }
}
