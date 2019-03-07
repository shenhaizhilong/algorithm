package com.hui.algorithm;

/**
 *https://leetcode-cn.com/problems/factorial-trailing-zeroes/description/
 *
 * https://www.cnblogs.com/hutonm/p/5624996.html
 *给定一个整数 n，返回 n! 结果尾数中零的数量。
 *
 * 示例 1:
 *
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * 示例 2:
 *
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 *
 *
 *  f(n!) = g(n!) = g(5^k * k! * a) = k + g(k!) = k + f(k!)
 * 所以，最终的计算公式为：
 * 当0 < n < 5时，f(n!) = 0;
 * 当n >= 5时，f(n!) = k + f(k!), 其中 k = n / 5（取整）。
 *
 * 计算举例
 * f(5!) = 1 + f(1!) = 1
 * f(10!) = 2 + f(2!) = 2
 * f(20!) = 4 + f(4!) = 4
 * f(100!) = 20 + f(20!) = 20 + 4 + f(4!) = 24
 * f(1000!) = 200 + f(200!) = 200 + 40 + f(40!) = 240 + 8 + f(8!) = 248 + 1 + f(1) =249
 *
 * from：http://www.cnblogs.com/wyqx/archive/2012/08/09/2630908.html
 *
 * @author: shenhaizhilong
 * @date: 2018/6/29 15:57
 */
public class TrailingZeroes {

    public static int trailingZeroes(int n) {

        int res = 0;
        while (n >=5)
        {

            n = n/5;
            res += n;
        }

        return res;
    }

}
