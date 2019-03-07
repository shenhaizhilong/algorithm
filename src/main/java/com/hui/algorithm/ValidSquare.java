package com.hui.algorithm;

import java.util.Arrays;

/**
 *
 * https://leetcode-cn.com/problems/valid-square/description/
 * 593. 有效的正方形
 * 给定二维空间中四点的坐标，返回四点是否可以构造一个正方形。
 *
 * 一个点的坐标（x，y）由一个有两个整数的整数数组表示。
 *
 * 示例:
 *
 * 输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * 输出: True
 *
 *
 * 注意:
 *
 * 所有输入整数都在 [-10000，10000] 范围内。
 * 一个有效的正方形有四个等长的正长和四个等角（90度角）。
 * 输入点没有顺序。
 *
 * @author: shenhaizhilong
 * @date: 2018/8/16 17:51
 */
public class ValidSquare {
    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] arr = pointSort(p1,p2,p3,p4);
        p1 = arr[0];
        p2 = arr[1];
        p4 = arr[2];
        p3= arr[3];
        int len1 = distance(p1,p2);
        int len2 = distance(p2,p3);
        int len3 = distance(p3,p4);
        int len4 = distance(p4,p1);
        int len5 = distance(p1,p3);
        if(len1 != len2 || len1 != len3 || len1 != len4 || len1 ==0)return false;
        if(len5 != 2*len1)return false;
        return true;

    }

    private static int distance(int[] p1, int[] p2)
    {
        int len = p2[0] - p1[0];
        int width = p2[1] - p1[1];
        return len*len + width*width;
    }

    private static int[][] pointSort(int[] p1, int[] p2, int[] p3, int[] p4)
    {
        int[][] arr = new int[4][2];
        arr[0] = p1;
        arr[1] = p2;
        arr[2] = p3;
        arr[3] = p4;
        for (int i = arr.length -1;i>0; i--) {
            findMax(arr,i);
        }

        return arr;

    }

    private static void findMax(int[][] arr, int end)
    {
        int[] max = arr[end];
        for (int i = 0; i < end; i++) {
            if(arr[i][0]> arr[end][0] || (arr[i][0] == arr[end][0] && arr[i][1] >arr[end][1]))
            {
                int[] temp = arr[end];
                arr[end] = arr[i];
                arr[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
       int[] p1 = {0,0}, p2 = {1,1}, p3 = {1,0}, p4 = {1,1};
       int[][] a = pointSort(p1,p2,p3,p4);
        for (int i = 0; i < a.length; i++) {
            System.out.println(Arrays.toString(a[i]));
        }

        System.out.println(validSquare(p1,p2,p3,p4));

    }

}
