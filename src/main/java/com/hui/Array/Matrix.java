package com.hui.Array;

import java.util.Arrays;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/21 18:48
 */
public class Matrix {


    public static void print(boolean[] matrix)
    {

        System.out.println(Arrays.toString(matrix));

    }

    public static void print(byte[] matrix)
    {

        System.out.println(Arrays.toString(matrix));

    }


    public static void print(char[] matrix)
    {

        System.out.println(Arrays.toString(matrix));

    }

    public static void print(short[] matrix)
    {

        System.out.println(Arrays.toString(matrix));

    }


    public static void print(int[] matrix)
    {

        System.out.println(Arrays.toString(matrix));

    }


    public static void print(long[] matrix)
    {

        System.out.println(Arrays.toString(matrix));

    }

    public static void print(float[] matrix)
    {

        System.out.println(Arrays.toString(matrix));

    }


    public static void print(double[] matrix)
    {

        System.out.println(Arrays.toString(matrix));

    }


    public static void print(boolean[][] matrix)
    {
        for(boolean[] m:matrix)
        {
            System.out.println(Arrays.toString(m));
        }
    }

    public static void print(byte[][] matrix)
    {
        for(byte[] m:matrix)
        {
            System.out.println(Arrays.toString(m));
        }
    }


    public static void print(char[][] matrix)
    {
        for(char[] m:matrix)
        {
            System.out.println(Arrays.toString(m));
        }
    }

    public static void print(short[][] matrix)
    {
        for(short[] m:matrix)
        {
            System.out.println(Arrays.toString(m));
        }
    }

    public static void print(int[][] matrix)
    {
        for(int[] m:matrix)
        {
            System.out.println(Arrays.toString(m));
        }
    }

    public static void print(long[][] matrix)
    {
        for(long[] m:matrix)
        {
            System.out.println(Arrays.toString(m));
        }
    }

    public static void print(float[][] matrix)
    {
        for(float[] m:matrix)
        {
            System.out.println(Arrays.toString(m));
        }
    }


    public static void print(double[][] matrix)
    {
        for(double[] m:matrix)
        {
            System.out.println(Arrays.toString(m));
        }
    }

    public  static <T> void print(List<T> lists)
    {
        for(T m:lists)
        {
            System.out.println(m);
        }
    }

    public static void print(int[][][] matrix)
    {
        for(int[][] m:matrix)
        {
           print(m);
            System.out.println("***************");
        }
    }

    public  static  void print(String[] strs)
    {
        System.out.println(Arrays.toString(strs));
    }

}
