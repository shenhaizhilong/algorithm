package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/25 18:56
 */
public class CutPod {


    public static int bottomUpCutPod(int[] p, int n)
    {
        if(n > p.length -1)throw new IllegalArgumentException("n must be less than p.length -1");
        int[] r = new int[n + 1];
        r[0] = 0;

        // 按顺序求解规模为j的子问题, j = 1,2,3,...n
        for (int j = 1; j <= n; j++) {
            int currentP = Integer.MIN_VALUE;

            // 求解规模为j 的子问题，从左边切i，右边为j-i
            for (int i = 1; i <= j; i++) {
                currentP = Math.max(currentP, p[i] +  r[j-i]);
            }

            r[j] = currentP;
        }

        return r[n];
    }

    public static int[] extentBottomUpCutPod(int[] p, int n)
    {
        if(n > p.length -1)throw new IllegalArgumentException("n must be less than p.length -1");
        int[] r = new int[n + 1];
        int[] s = new int[n + 1];
        int[] results = new int[2];
        r[0] = 0;
        for (int j = 1; j <=n ; j++) {
            int currentP = Integer.MIN_VALUE;

            for (int i = 1; i <= j; i++) {
                if(currentP < p[i] + r[j-i])
                {
                    currentP = p[i] + r[j-i];
                    s[j] = i;
                }
            }
            r[j] = currentP;
        }

        results[0] = s[n];
        results[1] = r[n];
        return results;
    }
    public static void main(String[] args) {

        int[] p = {0,1,5,8,9,10,17,17,20,24,30};
        for (int i = 1; i < 11; i++) {
            System.out.println(bottomUpCutPod(p, i));
        }

        for (int i = 0; i < 11; i++) {
            System.out.println(Arrays.toString(extentBottomUpCutPod(p,i)));
        }


    }
}
