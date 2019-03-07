package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/16 20:35
 *
 *
 * 546. Remove Boxes
 * DescriptionHintsSubmissionsDiscussSolution
 * Given several boxes with different colors represented by different positive numbers.
 * You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
 * Find the maximum points you can get.
 *
 * Example 1:
 * Input:
 *
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * Output:
 * 23
 * Explanation:
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 * ----> [1, 3, 3, 3, 1] (1*1=1 points)
 * ----> [1, 1] (3*3=9 points)
 * ----> [] (2*2=4 points)
 * Note: The number of boxes n would not exceed 100.
 *
 *
 */
public class RemoveBoxes {


    public int removeBoxes(int[] boxes) {

        int N = boxes.length;
        int[][][] cache = new int[N][N][N];
        return dfs(boxes, 0, N -1, 0, cache);
    }

    /**
     *
     *
     * https://leetcode.com/problems/remove-boxes/discuss/101310/Java-top-down-and-bottom-up-DP-solutions
     *
     *  the max point through removing box from start to end with k elements equals with boxes[start] at left.
     * @param boxes
     * @param start
     * @param end
     * @param k
     * @param cache
     * @return
     */
    private int dfs(int[] boxes, int start, int end, int k, int[][][] cache)
    {
        if(start > end)return 0;
        if(cache[start][end][k] > 0)return cache[start][end][k];
        for (; start + 1 <= end && boxes[start + 1] == boxes[start]; start++, k++); // optimization: all boxes of the same color counted continuously from the first box should be grouped together
        int max = (k + 1) * (k + 1) + dfs(boxes, start + 1, end, 0, cache);
        for (int i = start +1; i <=end; i++) {
            if(boxes[i] == boxes[start])
            {
                int m = i;
                int sum = dfs(boxes, start +1, m -1, 0, cache) + dfs(boxes,m ,end, k +1, cache);
                max = Math.max(max, sum);
            }
        }

        cache[start][end][k] = max;
        return max;
    }

    public static void main(String[] args) {

        RemoveBoxes removeBoxes = new RemoveBoxes();
        System.out.println(removeBoxes.removeBoxes(new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1}));
    }

}
