package com.hui.algorithm;


import java.util.Arrays;

/**
 *
 *
 * https://www.zhihu.com/question/21923021
 * http://wiki.jikexueyuan.com/project/kmp-algorithm/define.html
 *
 * @author: shenhaizhilong
 * @date: 2018/10/1 16:26
 */
public class KMP {

    public int[] getNext(String pattern)
    {
        int len = pattern.length();
        int[] next = new int[len];
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < pattern.length() -1)
        {
            //p[j] means the postfix, p[k] means the prefix
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k))
            {
                k++;
                j++;
                //next[j] = k 代表 p[j] 之前的模式串子串中，有长度为 k 的相同前缀和后缀
                next[j] = k;
            }else {
                // 当P[j] != P[k] 则将模式字符串向右滑动至模式中的第next[k]个字符与主串中的第j个字符比较,因为前k个字符串相等
                k = next[k];
            }
        }
        return next;

    }


    /**
     * partial match table
     * @param pattern
     * @return
     */
    public int[] getPMT(String pattern)
    {
        int len = pattern.length();
        int[] PMT = new int[len];
        PMT[0] = 0;
        int i = 1;
        int k = 0;
        while (i < len)
        {
            if(pattern.charAt(i) == pattern.charAt(k))
            {
                // k is the index, but if k + 1, then k + 1 is the len from index 0
                k++;
                PMT[i] = k;  // longest common prefix and suffix's length is k, k is the length
                i++;
            }else {
                if(k != 0)
                {
                    k = PMT[k  -1];
                }else
                {
                    PMT[i] = 0;
                    i++;
                }

            }
        }

        return PMT;
    }


    /**
     * 算法导论 第32章 字符串匹配
     * @param pattern
     * @return
     */
    public int[] prefixFun(String pattern)
    {
        int len = pattern.length();
        int[] P = new int[len];
        P[0] = 0;
        int k = 0;
        for (int i = 1; i < len; i++) {
            while (k > 0 && pattern.charAt(i) != pattern.charAt(k))
            {
                k = P[k - 1];
            }

            if(pattern.charAt(i) == pattern.charAt(k))
            {
                k++;
            }
            P[i] = k;
        }



        return P;
    }

    public int KMPMatch(String s, String parrern)
    {
        int i = 0;
        int j = 0;
        int lenS = s.length();
        int lenP = parrern.length();
        int[] next = getNext(parrern);
        while (i < lenS && j < lenP)
        {
            // if j == -1 or s[i] == p[j], just i++ and j++
            if(j == -1 || s.charAt(i) == parrern.charAt(j))
            {
                i++;
                j++;
            }else {
                // if j != -1, and s[i] != p[j], we didn't change i, we change j , next[j] is  k, so p0p1p2...pk-1 = pj - k ....pj-1
                //
                //
                // S[i-k]..    ...S[i-1]S[i]
                // P[0]P[1]P[2]...P[k-1]P[k] .....P[j - k] ....P[j-1]P[j]
               //  P[j - k] ..    P[j-1]P[j]i
                // let  j = next[j],
                // 当P[i] != P[j] 则将模式字符串向右滑动至模式中的第next[j]个字符与主串中的第i个字符比较，因为前next[j]个字符串相等
                j = next[j];
            }
        }

        // match, return it's position
        if(j == lenP)
        {
            return i - j;
        }
        // not match
        return -1;
    }


    public int bruteForceSearch(String s, String pattern)
    {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < pattern.length())
        {
            if(s.charAt(i) == pattern.charAt(j))
            {
                i++;
                j++;
            }else {
                i = i - j + 1;   // i 回溯
                j = 0;  // j 回溯
            }
        }

        if(j == pattern.length())
        {
            return i - j;
        }
        return -1;


    }

    public static void main(String[] args) {

        KMP kmp = new KMP();
        System.out.println(kmp.KMPMatch("BBC ABCDAB ABCDABCDABDE", "ABCDABD"));
        System.out.println(kmp.bruteForceSearch("BBC ABCDAB ABCDABCDABE", "ABCDABD"));
        System.out.println("*************************************************");
        System.out.println(Arrays.toString(kmp.getPMT("DABCDABDE")));
        System.out.println(Arrays.toString(kmp.getPMT("ABCDABD")));
        System.out.println(Arrays.toString(kmp.getPMT("ababa")));
        System.out.println(Arrays.toString(kmp.getPMT("aaaa")));
        System.out.println(Arrays.toString(kmp.getPMT("abac#caba")));
        System.out.println(Arrays.toString(kmp.getPMT("cacacabc")));
        System.out.println("*************************************************");
        System.out.println(Arrays.toString(kmp.prefixFun("DABCDABDE")));
        System.out.println(Arrays.toString(kmp.prefixFun("ABCDABD")));
        System.out.println(Arrays.toString(kmp.prefixFun("ababa")));
        System.out.println(Arrays.toString(kmp.prefixFun("aaaa")));
        System.out.println(Arrays.toString(kmp.prefixFun("abac#caba")));
        System.out.println(Arrays.toString(kmp.prefixFun("cacacabc")));
    }
}
