package com.hui.DynamicPrograming;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/6 20:55
 */
public class UniqueBinarySearchTrees {

    /**
     *
     * 96. Unique Binary Search Trees
     * DescriptionHintsSubmissionsDiscussSolution
     * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
     *
     * Example:
     *
     * Input: 3
     * Output: 5
     * Explanation:
     * Given n = 3, there are a total of 5 unique BST's:
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     *
     *
     *https://leetcode.com/problems/unique-binary-search-trees/discuss/31707/Fantastic-Clean-Java-DP-Solution-with-Detail-Explaination
     *
     * First note that dp[k] represents the number of BST trees built from 1....k;
     *
     * Then assume we have the number of the first 4 trees: dp[1] = 1 ,dp[2] =2 ,dp[3] = 5, dp[4] =14 , how do we get dp[5] based on these four numbers is the core problem here.
     *
     * The essential process is: to build a tree, we need to pick a root node, then we need to know how many possible left sub trees and right sub trees can be held under that node, finally multiply them.
     *
     * To build a tree contains {1,2,3,4,5}. First we pick 1 as root, for the left sub tree, there are none; for the right sub tree, we need count how many possible trees are there constructed from {2,3,4,5}, apparently it's the same number as {1,2,3,4}. So the total number of trees under "1" picked as root is dp[0] * dp[4] = 14. (assume dp[0] =1). Similarly, root 2 has dp[1]*dp[3] = 5 trees. root 3 has dp[2]*dp[2] = 4, root 4 has dp[3]*dp[1]= 5 and root 5 has dp[0]*dp[4] = 14. Finally sum the up and it's done.
     *
     * Now, we may have a better understanding of the dp[k], which essentially represents the number of BST trees with k consecutive nodes. It is used as database when we need to know how many left sub trees are possible for k nodes when picking (k+1) as root.
     *
     *  public int numTrees(int n) {
     *     int [] dp = new int[n+1];
     *     dp[0]= 1;
     *     dp[1] = 1;
     *     for(int level = 2; level <=n; level++)
     *         for(int root = 1; root<=level; root++)
     *             dp[level] += dp[level-root]*dp[root-1];
     *     return dp[n];
     * }
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <=n ; i++) {
            for (int root = 1; root <=i ; root++) {
                dp[i] += dp[root -1]*dp[i - root];
            }
        }
        return dp[n];
    }


    public int caltaLan3(int n)
    {

        // cantalan number
        // https://en.wikipedia.org/wiki/Catalan_number
        //https://en.wikipedia.org/wiki/Binomial_coefficient
        //C(2n,n)/(n+1)
        double ans = 1;
        double m = 1;
        for(int i=2;i<= n;i++){
            ans = ans*(i + n);
            m = m*i;
        }
        return (int) (ans/m);
    }

    public static void main(String[] args) {

        UniqueBinarySearchTrees uniqueBinarySearchTrees = new UniqueBinarySearchTrees();
        for (int i = 1; i < 20 ; i++) {
            System.out.println(uniqueBinarySearchTrees.caltaLan3(i));
            System.out.println(uniqueBinarySearchTrees.numTrees(i));
        }

        System.out.println(uniqueBinarySearchTrees.caltaLan3(18));
    }

}
