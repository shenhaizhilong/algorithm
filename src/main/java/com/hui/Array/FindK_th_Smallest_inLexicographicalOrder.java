package com.hui.Array;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/21 9:25
 */
public class FindK_th_Smallest_inLexicographicalOrder {


    /**
     *
     * 440. K-th Smallest in Lexicographical Order
     * DescriptionHintsSubmissionsDiscussSolution
     * Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.
     *
     * Note: 1 ≤ k ≤ n ≤ 109.
     *
     * Example:
     *
     * Input:
     * n: 13   k: 2
     *
     * Output:
     * 10
     *
     * Explanation:
     * The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
     *
     *
     *
     * https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/discuss/92242/ConciseEasy-to-understand-Java-5ms-solution-with-Explaination
     *
     * Original idea comes from
     * http://bookshadow.com/weblog/2016/10/24/leetcode-k-th-smallest-in-lexicographical-order/
     *
     * Actually this is a denary tree (each node has 10 children). Find the kth element is to do a k steps preorder traverse of the tree.
     * 0_1477293053966_upload-40379731-118a-4753-bed9-1cb372790d4b
     *
     * Initially, image you are at node 1 (variable: curr),
     * the goal is move (k - 1) steps to the target node x. (substract steps from k after moving)
     * when k is down to 0, curr will be finally at node x, there you get the result.
     *
     * we don't really need to do a exact k steps preorder traverse of the denary tree, the idea is to calculate the steps between curr and curr + 1 (neighbor nodes in same level), in order to skip some unnecessary moves.
     *
     * Main function
     * Firstly, calculate how many steps curr need to move to curr + 1.
     *
     * if the steps <= k, we know we can move to curr + 1, and narrow down k to k - steps.
     *
     * else if the steps > k, that means the curr + 1 is actually behind the target node x in the preorder path, we can't jump to curr + 1. What we have to do is to move forward only 1 step (curr * 10 is always next preorder node) and repeat the iteration.
     *
     * calSteps function
     *
     * how to calculate the steps between curr and curr + 1?
     * Here we come up a idea to calculate by level.
     * Let n1 = curr, n2 = curr + 1.
     * n2 is always the next right node beside n1's right most node (who shares the same ancestor "curr")
     * (refer to the pic, 2 is right next to 1, 20 is right next to 19, 200 is right next to 199).
     *
     * so, if n2 <= n, what means n1's right most node exists, we can simply add the number of nodes from n1 to n2 to steps.
     *
     * else if n2 > n, what means n (the biggest node) is on the path between n1 to n2, add (n + 1 - n1) to steps.
     *
     * organize this flow to "steps += Math.min(n + 1, n2) - n1; n1 *= 10; n2 *= 10;"
     *
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k = k -1;
        while (k > 0)
        {
            int steps = calcSteps(n, curr, curr +1);
            if(steps <= k)
            {
                curr += 1;
                k -= steps;
            }else {
                curr *= 10;
                k -= 1;
            }
        }
        return curr;

    }


    /**
     *
     * init ans = 1
     * while(k > 0) do this loop
     * calc the gap between ans and ans +1
     * if gap <= k, k = k - gap    we can move to ans + 1
     * else ans = ans*10, k = k -1, we can't move to ans + 1, so we need to go down next level to narrow down the gap
     * n ,p,q = 35769,3,4
     * p         q         gap
     * 3         4          1
     * 30        40         11
     * 300       400        111
     * 3000      4000       1111
     * 30000     40000      min(n +1,q) - p = 6881
     * @param n
     * @param n1
     * @param n2
     * @return
     */
    private int calcSteps(int n, long n1, long n2)
    {
        int steps = 0;
        while (n1 <= n)
        {
            steps += Math.min(n +1, n2) - n1;
            n1 *= 10;
            n2 *= 10;
        }
        return steps;
    }

    public static void main(String[] args) {
        FindK_th_Smallest_inLexicographicalOrder ksamll = new FindK_th_Smallest_inLexicographicalOrder();
        System.out.println(ksamll.findKthNumber(209, 4));
    }


}
