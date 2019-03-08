package com.hui.DynamicPrograming;

/**
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/23/dynamic-programming/54/
 * 爬楼梯
 * 假设你正在爬楼梯。需要 n 步你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 步 + 1 步
 * 2.  2 步
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 步 + 1 步 + 1 步
 * 2.  1 步 + 2 步
 * 3.  2 步 + 1 步
 * @author: shenhaizhilong
 * @date: 2018/6/14 12:32
 */
public class Fib {


    public static long count(int n)
    {
        if(n<=0)
            return 0;
        if(n>91)
            throw new IllegalArgumentException("n must less than 91");
        long temp;
        long f0 = 0;
        long f1 = 1;
        for (int i = 0; i < n; i++) {
            temp = f1;
            f1 = f0 + f1;
            f0 = temp;
        }
        return f1;
    }

    // O(n) compute fibonacci numbers
    public static long fib(int n)
    {

        //
        if(n > 92)
            throw new IllegalArgumentException("n must less than 93");
        long curr = 0;
        long prev = 1;
        long next;
        for (int i = 0; i < n; i++) {
            next = curr + prev;
            prev = curr;
            curr = next;
        }
        return curr;
    }

    public static long fib2(int n)
    {
        if(n<=0)
            return 0;
        return fastFib(n)[1];
    }

    // Algorithms jeff2-2up  3 dynamic programming
    // compute the pair <<F(n-1),F(n)>>
    // log(n)
    private static long[] fastFib(int n)
    {
        if(n == 1)
            return new long[]{0,1};
        int m = n >>> 1;
        long[] fibm = fastFib(m);  // <<F(m-1),F(m)>>
        long prev = fibm[0]*fibm[0] + fibm[1]*fibm[1];  // F(2m -1)
        long curr = fibm[1]*(2*fibm[0] + fibm[1]); // F(2m)
        long next = prev + curr;
        if((n & 0x01) == 0)return new long[]{prev,curr};
        return new long[]{curr,next};
    }


    public static void main(String[] args) {

            for (int i = 0; i < 93; i++) {
                System.out.println("n is: " + i + " , " + fib(i) + ", " + fib2(i));
            }
        System.out.println(Long.MAX_VALUE);

    }
}
