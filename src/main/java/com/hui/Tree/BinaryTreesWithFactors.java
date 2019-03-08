package com.hui.Tree;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/29 0:18
 *
 *
 */
public class BinaryTreesWithFactors {


    public int numFactoredBinaryTrees(int[] A) {
        if(A == null || A.length < 1)return 0;
        int Mod = 1000000007;
        Arrays.sort(A);
        Map<Integer,Long> dp = new HashMap<>();
        long ans = 0;
        for (int i = 0; i < A.length; i++) {
            dp.put(A[i],1L);
            for (int j = 0; j < i; j++) {
                int q = A[i]/A[j];
                if(A[i] % A[j] == 0 && dp.containsKey(q))
                {
                    long value = (dp.get(A[i]) + dp.get(A[j])*dp.get(q)) % Mod;
                    dp.put(A[i], value);
                }
            }
        }

        for(long v:dp.values())
        {
            ans = (ans + v) % Mod;
        }
        return (int)ans;
    }

    public static void main(String[] args) {

        BinaryTreesWithFactors binaryTreesWithFactors = new BinaryTreesWithFactors();
        System.out.println(binaryTreesWithFactors.numFactoredBinaryTrees(new int[]{2,4}));
        System.out.println(binaryTreesWithFactors.numFactoredBinaryTrees(new int[]{2,4,5,10}));
    }
}
