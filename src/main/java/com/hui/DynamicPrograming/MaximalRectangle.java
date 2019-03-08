package com.hui.DynamicPrograming;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/4 21:43
 *
 *85. Maximal Rectangle
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *
 * Example:
 *
 * Input:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * Output: 6
 *
 *
 *
 *
 *
 * https://leetcode.com/problems/maximal-rectangle/discuss/29054/Share-my-DP-solution
 *
 *  height[i] record the current number of countinous '1' in column i;
 *  * left[i] record the left most index j which satisfies that for any index k from j to  i, height[k] >= height[i];
 *  * right[i] record the right most index j which satifies that for any index k from i to  j, height[k] >= height[i];
 *  * by understanding the definition, we can easily figure out we need to update maxArea with value (height[i] * (right[i] - left[i] + 1));
 *  *
 *  * Then the question is how to update the array of height, left, and right
 *  * =================================
 *  * for updating height, it is easy
 *  * for (int j = 0; j < n; j++) {
 *  *    if (matrix[i][j] == '1') height[j]++;
 *  *    else height[j] = 0;
 *  * }
 *  * =============================================================================
 *  * It is a little bit tricky for initializing and updating left and right array
 *  * for initialization:
 *  * we know initially, height array contains all 0, so according to the definition of left and right array, left array should contains all 0, and right array should contain all n - 1
 *  * for updating left and right, it is kind of tricky to understand...
 *  *     ==============================================================
 *  *     Here is the code for updating left array, we scan from left to right
 *  *     int lB = 0;  //lB is indicating the left boundry for the current row of the matrix (for cells with char "1"), not the height array...
 *  *     for (int j = 0; j < n; j++) {
 *  *          if (matrix[i][j] == '1') {
 *  *              left[j] = Math.max(left[j], lB); // this means the current boundry should satisfy two conditions: within the boundry of the previous height array, and within the boundry of the current row...
 *  *          } else {
 *  *              left[j] = 0; // when matrix[i][j] = 0, height[j] will get update to 0, so left[j] becomes 0, since all height in between 0 - j satisfies the condition of height[k] >= height[j];
 *  *              lB = j + 1; //and since current position is '0', so the left most boundry for next "1" cell is at least j + 1;
 *  *          }
 *  *     }
 *  *     ==============================================================
 *  *     the idea for updating the right boundary is similar, we just need to iterate from right to left
 *  *     int rB = n - 1;
 *  *     for (int j = n - 1; j >= 0; j--) {
 *  *         if (matrix[i][j] == '1') {
 *  *              right[j] = Math.min(right[j], rB);
 *  *         } else {
 *  *              right[j] = n - 1;
 *  *              rB = j - 1;
 *  *      }
 *
 */
public class MaximalRectangle {


    public int maximalRectangle(char[][] matrix) {

        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)return 0;
        int M = matrix.length;
        int N = matrix[0].length;
        int[] height = new int[N];
        int[] left = new int[N];
        int[] right = new int[N];

        int maxArea = 0;
        Arrays.fill(right, N -1);
        for (int i = 0; i < M; i++) {

            int RB = N -1;
            for (int j = N-1; j >=0; j--) {
                if(matrix[i][j] == '1')
                {
                    right[j] = Math.min(right[j], RB);
                }else {
                    right[j] = N -1;
                    RB = j -1;
                }
            }


            int LB = 0;
            for (int j = 0; j < N; j++) {
                if(matrix[i][j] == '1')
                {
                    left[j] = Math.max(left[j], LB);
                    height[j]++;
                    int area = (right[j] - left[j] +1)*height[j];
                    maxArea = Math.max(maxArea, area);
                }else {
                    left[j] = 0;
                    LB = j +1;
                    height[j] = 0;
                }
            }

        }

        return maxArea;
    }


    public static void main(String[] args) {

        MaximalRectangle maximalRectangle = new MaximalRectangle();
        System.out.println(maximalRectangle.maximalRectangle(new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}

        }));
    }
}
