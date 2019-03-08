package com.hui.DynamicPrograming;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/2 11:25
 */
public class Pascal {
    private Map<Pair<Integer,Integer>, Integer> memo = new HashMap<>();
    public int calc(int n, int k)
    {
        if(k == 0)return 1;
        if(n == 0)return 0;
        Pair<Integer,Integer> pair = new Pair<>(n, k);
        if(memo.containsKey(pair))return memo.get(pair);
        int ans = calc(n-1, k -1) + calc(n-1, k);
        memo.put(pair, ans);
        return ans;
    }

    public int calc2(int n, int k)
    {
        int[][] dp = new int[n+1][k+1];
        dp[0][0] = 1;
        for (int i = 1; i <=n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(k, i); j++) {
                dp[i][j] = dp[i -1][j -1] + dp[i-1][j];
            }
        }

        return dp[n][k];
    }

    public int calc3(int n, int k)
    {
        int[] dp = new int[k+1];
        for (int i = 1; i <=n; i++) {
            dp[0] = 1;
            for (int j = Math.min(k, i); j >=1; j--) {
                dp[j] = dp[j -1] + dp[j];
            }
        }

        return dp[k];
    }


    public static void main(String[] args) {
        Pascal pascal = new Pascal();
        System.out.println(pascal.calc(10,7));
        System.out.println(pascal.calc2(10,7));
        System.out.println(pascal.calc3(10,7));


        System.out.println("***************");
        System.out.println(pascal.calc(4,2));
        System.out.println(pascal.calc2(4,2));
        System.out.println(pascal.calc3(4,2));

    }
}
