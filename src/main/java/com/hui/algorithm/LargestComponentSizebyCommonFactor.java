package com.hui.algorithm;


import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/4 11:28
 *
 *
 */
public class LargestComponentSizebyCommonFactor {

    private static boolean[] notPrime;

    public int largestComponentSize(int[] A) {
        int N = A.length;
        int maxSize = 0;
        UnionFindII unionFindII = new UnionFindII(N);
        for (int i = 0; i < N; i++) {
            for (int j = i +1; j < N; j++) {
                int factor = gcd(A[i],A[j]);
                if(factor > 1)
                {
                    unionFindII.union(i,j);
                    maxSize = Math.max(maxSize, unionFindII.getSize(i));
                }
            }
        }

        return maxSize;

    }

    private int gcd(int a, int b)
    {

        while (a != 0 && b != 0)
        {
            int t = b;
            b = a%b;
            a = t;
        }
        return a + b;
    }


    public int largestComponentSize2(int[] A) {
        int N = A.length;
        int maxSize = 1;
        UnionFindII unionFindII = new UnionFindII(100001);
        for (int i = 0; i < N; i++) {

            int a = A[i];
            //Union each number with all its factor;
            for (int k = 2; k*k <= a; k++) {
                if(a % k == 0)
                {
                    unionFindII.union(a,k);
                    unionFindII.union(a, a/k);
                }
            }
        }

        //(2) Count the most frequent parent.
        Map<Integer,Integer> map = new HashMap<>();
        for(int a:A)
        {
            int parent = unionFindII.find(a);
            int freq = map.getOrDefault(parent,0) + 1;
            map.put(parent, freq);
            maxSize = Math.max(maxSize, freq);
        }

        return maxSize;

    }


    public static void main(String[] args) {
        LargestComponentSizebyCommonFactor largestComponentSizebyCommonFactor = new LargestComponentSizebyCommonFactor();
        System.out.println(largestComponentSizebyCommonFactor.largestComponentSize(new int[]{4,6,15,35}));
        System.out.println(largestComponentSizebyCommonFactor.largestComponentSize(new int[]{20,50,9,63}));
        System.out.println(largestComponentSizebyCommonFactor.largestComponentSize(new int[]{2,3,6,7,4,12,21,39}));

        System.out.println("*****************");

        System.out.println(largestComponentSizebyCommonFactor.largestComponentSize2(new int[]{4,6,15,35}));
        System.out.println(largestComponentSizebyCommonFactor.largestComponentSize2(new int[]{20,50,9,63}));
        System.out.println(largestComponentSizebyCommonFactor.largestComponentSize2(new int[]{2,3,6,7,4,12,21,39}));
    }
}
