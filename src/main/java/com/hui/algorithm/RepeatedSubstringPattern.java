package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/4 17:41
 */
public class RepeatedSubstringPattern {


    /**
     *
     * 459. Repeated Substring Pattern
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
     *
     *
     *
     * Example 1:
     *
     * Input: "abab"
     * Output: True
     * Explanation: It's the substring "ab" twice.
     * Example 2:
     *
     * Input: "aba"
     * Output: False
     * Example 3:
     *
     * Input: "abcabcabcabc"
     * Output: True
     * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
     *
     *
     * 假设 S = k*m, 即S 有 k 个子字符串m 组成, k >=2
     * 2S = 2k*m
     * 现在我们移除掉2S字符串的第一个和最后一个字符，
     * S' = (2S).subString(1,2S.length() -1)
     * 那么S' 中至少还有 2k -2 个m,又因为 2k-2 >= k，所以S' 中应该包含一个S
     *
     * beats, 49%
     * @param s
     * @return
     */
    public static boolean repeatedSubstringPattern(String s) {

        StringBuilder sb = new StringBuilder(s);
        sb.append(s);
        String s1 = sb.substring(1,sb.length() -1);
        return s1.indexOf(s) != -1;

    }


    /**
     *
     * 14ms. beats 91.11%
     * @param s
     * @return
     */
    public static   boolean repeatedSubstringPattern2(String s) {
        
        return checkEven(s) || checkOdd(s);
    }

    /**
     *
     * 偶数个子字符串
     * @param s
     * @return
     */
    private static boolean checkEven(String s)
    {
        int Len = s.length()/2;
        return (s.length() & 0x01) == 0 && s.substring(0,Len).equals(s.substring(Len,s.length()));
    }

    /**
     * 奇数个子字符串
     * @param s
     * @return
     */
    private static boolean checkOdd(String s)
    {
        int len = s.length();
        for (int i = 3; i <= len; i +=2)
        {
            if(len % i == 0)
            {
                int k = len /i; //每个子字符串的长度
                StringBuilder sb = new StringBuilder();
                String sub = s.substring(0,k);
                for (int j = 0; j < len; j+=k) {
                    sb.append(sub);
                }
                if(sb.toString().equals(s))return true;
            }
        }

        return false;
    }

        public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern2("aaa"));
        System.out.println(repeatedSubstringPattern2("ababab"));
        System.out.println(repeatedSubstringPattern2("aba"));
        System.out.println(repeatedSubstringPattern2("abcabcabcabc"));
        System.out.println(repeatedSubstringPattern2("abcabcabc"));
        System.out.println(repeatedSubstringPattern2("abcabd"));
    }

}
