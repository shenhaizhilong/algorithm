package com.hui.Math;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * https://leetcode-cn.com/problems/pascals-triangle/description/
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 *
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 * @author: shenhaizhilong
 * @date: 2018/8/14 17:31
 */
public class PasscalTriangle {

    public static List<List<Integer>> generate(int numRows)
    {
        List<List<Integer>> res = new ArrayList<>(numRows);
        if(numRows < 1)return res;
        int rowNum = 1;
        List<Integer> row = null;
        while (rowNum <= numRows)
        {
            row = new ArrayList<>(rowNum);
            row.add(1);
            if(rowNum > 2)
            {
                for (int i = 1; i < rowNum -1 ; i++) {
                    row.add(res.get(rowNum -2).get(i-1) + res.get(rowNum -2).get(i));
                }
            }
            //at last add(1)
            if(rowNum >=2)
            {
                row.add(1);
            }

            res.add(row);
            rowNum++;
        }

        return res;
    }


    /**
     *
     * https://leetcode.com/problems/pascals-triangle-ii/description/
     * Pascal's Triangle II
     * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
     *
     * Note that the row index starts from 0.
     *
     *
     * In Pascal's triangle, each number is the sum of the two numbers directly above it.
     *
     * Example:
     *
     * Input: 3
     * Output: [1,3,3,1]
     * Follow up:
     *
     * Could you optimize your algorithm to use only O(k) extra space?
     * @param rowIndex
     * @return
     */
    public static List<Integer> getRow(int rowIndex) {
        if(rowIndex < 0)throw new IllegalArgumentException("rowIndex can't be less than zero");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        if(rowIndex == 0)return list;
        list.add(1);
        if(rowIndex == 1)return list;
        List<Integer> lastRow = null;
        while (list.size() -1 != rowIndex)
        {
            lastRow = list;
            list = new ArrayList<>();
            list.add(1);
            for (int i = 0; i < lastRow.size() -1; i++) {
                list.add(lastRow.get(i) + lastRow.get(i+1));
            }
            list.add(1);
        }

        return list;
    }

    public static void main(String[] args) {
     //   System.out.println(generate(0));

        for (int i = 0; i < 4; i++) {
            System.out.println(getRow(i));
        }
    }


}
