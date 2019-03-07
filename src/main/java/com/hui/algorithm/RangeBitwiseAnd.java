package com.hui.algorithm;

/**
 *https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/description/
 * 数字范围按位与
 * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
 *
 * 示例 1:
 *
 * 输入: [5,7]
 * 输出: 4
 * 示例 2:
 *
 * 输入: [0,1]
 * 输出: 0
 *
 * @author: shenhaizhilong
 * @date: 2018/7/1 0:53
 */
public class RangeBitwiseAnd {

    public static int rangeBitwiseAnd(int m, int n) {
        if(m == 0 || n ==0)return 0;
        if(m==n)return n;
        if((n &(n-1))==0) return 0;
        int results = Integer.MAX_VALUE;
        int powerOfTwo_m = BitUtil.cellingPowerOfTwo(m);

        if(powerOfTwo_m > m && powerOfTwo_m <n)
        {
           return 0;
        }
        System.out.println("m = " + m);
        System.out.println("n = " + n);
        for (int i = m; i <=n && i >0; i++) {
            results = results&i;
        }
        return results;
    }

    /**
     * 直接平移m和n，每次向右移一位，直到m和n相等，记录下所有平移的次数i，然后再把m左移i位即为最终结果
     *
     * @param m
     * @param n
     * @return
     */

    public static int rangeBitwiseAnd2(int m, int n) {

        int sum = 0;
        while (m !=n)
        {
            m >>>=1;
            n >>>=1;
            sum++;
        }
        m = m <<sum;
        return m;
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4431646.html
     *
     * [5, 7]里共有三个数字，分别写出它们的二进制为：
     *
     * 101　　110　　111
     *
     * 相与后的结果为100，仔细观察我们可以得出，最后的数是该数字范围内所有的数的左边共同的部分，如果上面那个例子不太明显，我们再来看一个范围[26, 30]，它们的二进制如下：
     *
     * 11010　　11011　　11100　　11101　　11110
     *
     * 发现了规律后，我们只要写代码找到左边公共的部分即可，我们可以从建立一个32位都是1的mask，然后每次向左移一位，比较m和n是否相同，不同再继续左移一位，直至相同，然后把m和mask相与就是最终结果，
     *
     * @param m
     * @param n
     * @return
     */
    public static int rangeBitwiseAnd3(int m, int n)
    {
        int mask = 0x7fffffff;
        while ((m&mask) != (n&mask))
        {
            mask = mask<<1;
        }
        return m &mask;
    }


    public static void main(String[] args) {

//        for (int i = 0; i < 100; i++) {
//            System.out.println(BitUtil.getBitsStr(i));
//        }
//
        int m = 9;
        int n  = 15;
        System.out.println(rangeBitwiseAnd(m,n));

    }
}
