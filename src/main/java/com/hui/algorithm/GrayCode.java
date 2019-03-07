package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/7 8:42
 */
public class GrayCode {


    /**
     *
     *
     * 89. Gray Code
     * DescriptionHintsSubmissionsDiscussSolution
     * The gray code is a binary numeral system where two successive values differ in only one bit.
     *
     * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
     *
     * Example 1:
     *
     * Input: 2
     * Output: [0,1,3,2]
     * Explanation:
     * 00 - 0
     * 01 - 1
     * 11 - 3
     * 10 - 2
     *
     * For a given n, a gray code sequence may not be uniquely defined.
     * For example, [0,2,3,1] is also a valid gray code sequence.
     *
     * 00 - 0
     * 10 - 2
     * 11 - 3
     * 01 - 1
     * Example 2:
     *
     * Input: 0
     * Output: [0]
     * Explanation: We define the gray code sequence to begin with 0.
     *              A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
     *              Therefore, for n = 0 the gray code sequence is [0].
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        int len =(int) Math.pow(2,n);
        List<Integer> res = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            res.add(binaryToGray(i));
        }
        return res;
    }

    /**
     *
     * Hacker's delight
     * @param n
     * @return
     */
    public int binaryToGray(int n)
    {
        return n^(n>>1);
    }


    public int grayToBinary32(int n)
    {
        n = n ^ (n >> 16);
        n = n ^ (n >> 8);
        n = n ^ (n >> 4);
        n = n ^ (n >> 2);
        n = n ^ (n >> 1);
        return n;
    }


    public static void main(String[] args) {

        GrayCode grayCode = new GrayCode();
        List<Integer> list = grayCode.grayCode(4);
        System.out.println(list);
        List<Integer> list1 = new ArrayList<>(list.size());
        for(int i: list)
        {
            list1.add(grayCode.grayToBinary32(i));
        }

        System.out.println(list1);
    }

}
