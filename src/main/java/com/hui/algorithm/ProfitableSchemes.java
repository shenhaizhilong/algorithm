package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/27 10:37
 *
 *
 * 879. Profitable Schemes
 * DescriptionHintsSubmissionsDiscussSolution
 * There are G people in a gang, and a list of various crimes they could commit.
 *
 * The i-th crime generates a profit[i] and requires group[i] gang members to participate.
 *
 * If a gang member participates in one crime, that member can't participate in another crime.
 *
 * Let's call a profitable scheme any subset of these crimes that generates at least P profit, and the total number of gang members participating in that subset of crimes is at most G.
 *
 * How many schemes can be chosen?  Since the answer may be very large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: G = 5, P = 3, group = [2,2], profit = [2,3]
 * Output: 2
 * Explanation:
 * To make a profit of at least 3, the gang could either commit crimes 0 and 1, or just crime 1.
 * In total, there are 2 schemes.
 * Example 2:
 *
 * Input: G = 10, P = 5, group = [2,3,5], profit = [6,7,8]
 * Output: 7
 * Explanation:
 * To make a profit of at least 5, the gang could commit any crimes, as long as they commit one.
 * There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).
 *
 *
 * Note:
 *
 * 1 <= G <= 100
 * 0 <= P <= 100
 * 1 <= group[i] <= 100
 * 0 <= profit[i] <= 100
 * 1 <= group.length = profit.length <= 100
 *
 */
public class ProfitableSchemes {


    int Mod = 1_000_000_007;
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {

        // memo[k,p,g] means  with k elements, k in range[1,group.length],
        // at least with p profit and with g members. what is the total schemes can be chosen.
        //dp(k, p, g) = dp(k - 1, p, g) + dp(k - 1, p - profit(k - 1), g - group(k - 1))
        int[][][] memo = new int[group.length +1][P +1][G +1];
        int ans = dfs(group.length,  P, G, memo, group, profit);
        Matrix.print(memo);
        return ans;
    }

    private int dfs(int k,  int P, int G, int[][][] memo, int[] group, int[] profit)
    {
       if(k < 1)return P <= 0 ? 1: 0;
       if(P < 0) P = 0;
       if(memo[k][P][G] > 0)return memo[k][P][G];
       long res = dfs(k -1, P, G, memo, group, profit);
       if(G >= group[k -1])
       {
           res += dfs(k -1, P - profit[k -1],G - group[k -1],  memo, group, profit);
       }
       res %= Mod;
       memo[k][P][G] = (int)res;
       return (int)res;
    }

    public int profitableSchemes2(int G, int P, int[] group, int[] profit) {
        //dp[k][i][j] means for first k crime with j members and at least i profit, what is total schemes can be chosen.
        int[][][] dp = new int[group.length + 1][P + 1][G + 1];
        dp[0][0][0] = 1;
        for (int k = 1; k <= group.length; k++) {
            int g = group[k - 1];
            for (int i = 0; i <= P; i++) {
                for (int j = 0; j <= G; j++) {
                    dp[k][i][j] = dp[k - 1][i][j];
                    if (j >= g) {
                        dp[k][i][j] = (dp[k -1][i][j] + dp[k - 1][Math.max(0,  i - profit[k - 1])][j - g])%Mod;
                    }
                }
            }
        }

        Matrix.print(dp);

        int sum = 0;
        for(int i = 0; i <= G; i++){
            sum = (sum + dp[group.length][P][i])%Mod;
        }
        return sum;
    }

    public int profitableSchemes3(int G, int P, int[] group, int[] profit) {

        //dp[k][i][j] means for first k crime with j members and at least i profit, what is total schemes can be chosen.
        //dp(k, p, g) = dp(k - 1, p, g) + dp(k - 1, p - profit(k - 1), g - group(k - 1))
        int k = group.length;
        int[][] dp = new int[P +1][G +1];
        dp[0][0] = 1;

        for (int i = 1; i <=k ; i++) {
            for (int p = P; p >=0; p--) {
                int temG = group[i -1];
                for (int g = G;g >= temG; g--) {
                    dp[p][g] =  (dp[p][g] +dp[Math.max(p - profit[i -1], 0)][g - temG])%Mod;

                }
            }
        }

        int ans = 0;
        for(int n: dp[P])
        {
            ans = (ans + n) % Mod;
        }

        Matrix.print(dp);

        return  ans;
    }


    public static void main(String[] args) {
        int G = 5, P = 3;
        int[] group = {2,2}, profit = {2,3};
        ProfitableSchemes profitableSchemes =  new ProfitableSchemes();
        System.out.println(profitableSchemes.profitableSchemes(G, P, group, profit));
        System.out.println(profitableSchemes.profitableSchemes(10, 5, new int[]{2,3,5}, new int[]{6,7,8}));

        System.out.println("*******************");
        System.out.println(profitableSchemes.profitableSchemes2(G, P, group, profit));
        System.out.println(profitableSchemes.profitableSchemes2(10, 5, new int[]{2,3,5}, new int[]{6,7,8}));


        System.out.println("*******************");
        System.out.println(profitableSchemes.profitableSchemes3(G, P, group, profit));
        System.out.println(profitableSchemes.profitableSchemes3(10, 5, new int[]{2,3,5}, new int[]{6,7,8}));

    }
}
