package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/26 23:15
 *
 *
 * 808. Soup Servings
 * DescriptionHintsSubmissionsDiscussSolution
 * There are two types of soup: type A and type B. Initially we have N ml of each type of soup. There are four kinds of operations:
 *
 * Serve 100 ml of soup A and 0 ml of soup B
 * Serve 75 ml of soup A and 25 ml of soup B
 * Serve 50 ml of soup A and 50 ml of soup B
 * Serve 25 ml of soup A and 75 ml of soup B
 * When we serve some soup, we give it to someone and we no longer have it.  Each turn, we will choose from the four operations with equal probability 0.25. If the remaining volume of soup is not enough to complete the operation, we will serve as much as we can.  We stop once we no longer have some quantity of both types of soup.
 *
 * Note that we do not have the operation where all 100 ml's of soup B are used first.
 *
 * Return the probability that soup A will be empty first, plus half the probability that A and B become empty at the same time.
 *
 *
 *
 * Example:
 * Input: N = 50
 * Output: 0.625
 * Explanation:
 * If we choose the first two operations, A will become empty first. For the third operation, A and B will become empty at the same time. For the fourth operation, B will become empty first. So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.
 *
 * Notes:
 *
 * 0 <= N <= 10^9.
 * Answers within 10^-6 of the true value will be accepted as correct.
 *
 *
 */
public class SoupServings {

    public double soupServings1(int N) {
        if(N >= 4800)return 1;
        double[][] memo = new double[N+1][N+1];
        double ans = dfs1(N, N, memo);
        Matrix.print(memo);
        return ans;


    }

    private double dfs1(int A, int B, double[][] memo)
    {
        if(A <= 0 && B > 0) return 1;
        if(A <= 0 && B <= 0)return 0.5;
        if(B <=0 )return 0;
        if(memo[A][B] > 0)return memo[A][B];
        memo[A][B] = 0.25*(dfs1(A -100,B, memo)
                + dfs1(A -75, B -25, memo)
                + dfs1(A -50, B -50, memo)
                + dfs1(A -25, B -75, memo));
        return memo[A][B];

    }

    public double soupServings(int N) {
        if(N >= 4800)return 1;
        if(N % 25 == 0){
            N = N/25;
        }else {
            N = N/25 +1;
        }
        double[][] memo = new double[N+1][N+1];
        double ans = dfs(N, N, memo);
        Matrix.print(memo);
        return ans;

    }

    private double dfs(int A, int B, double[][] memo)
    {
        if(A <= 0 && B > 0) return 1;
        if(A <= 0 && B <= 0)return 0.5;
        if(B <=0 )return 0;
        if(memo[A][B] > 0)return memo[A][B];
        memo[A][B] = 0.25*(dfs(A -4,B, memo)
                + dfs(A -3, B -1, memo)
                + dfs(A - 2, B -2, memo)
                + dfs(A -1, B -3, memo));
        return memo[A][B];

    }

    public static void main(String[] args) {
        SoupServings soupServings = new SoupServings();
        System.out.println( soupServings.soupServings1(75));
        System.out.println( soupServings.soupServings(75));
//        for (int i = 4800; i < 5200; i++) {
//            System.out.println(i + ", " + soupServings.soupServings1(i));
//            System.out.println(i + ", " + soupServings.soupServings(i));
//            System.out.println("************************");
//        }

    }

}
