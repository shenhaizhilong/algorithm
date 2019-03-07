package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/12 15:20
 *
 *
 *467. Unique Substrings in Wraparound String
 * DescriptionHintsSubmissionsDiscussSolution
 * Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 *
 * Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.
 *
 * Note: p consists of only lowercase English letters and the size of p might be over 10000.
 *
 * Example 1:
 * Input: "a"
 * Output: 1
 *
 * Explanation: Only the substring "a" of string "a" is in the string s.
 * Example 2:
 * Input: "cac"
 * Output: 2
 * Explanation: There are two substrings "a", "c" of string "cac" in the string s.
 * Example 3:
 * Input: "zab"
 * Output: 6
 * Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.
 *
 */
public class UniqueSubstringsinWraparoundString {

    public int findSubstringInWraproundString(String p) {

        //dp[i] means max number of substring (substring present in abcdefghijklmnopqrstuvwxyz )  ending with (i +'a') , where i in [0,25]
      int[] dp = new int[26];
      int maxNum =0;
        for (int i = 0; i < p.length(); i++) {
            if(i > 0 && (p.charAt(i) - p.charAt(i -1) +26) %26 ==1)
            {
                maxNum++;
            }else {
                maxNum = 1;
            }
            int id = p.charAt(i) - 'a';
            dp[id] = Math.max(dp[id], maxNum);
        }

        int ans = 0;
        for(int i:dp)
        {
            ans += i;
        }

        return ans;

    }

    public int findSubstringInWraproundString2(String p) {

        //dp[i] means max number of substring (substring present in abcdefghijklmnopqrstuvwxyz )  ending with (i +'a') , where i in [0,25]
        int[] dp = new int[26];
        int maxNum =0;
        for (int i = 0; i < p.length(); i++) {
            if(i > 0 && (p.charAt(i) - p.charAt(i -1) == 1 || (p.charAt(i-1) - p.charAt(i) == 25)))
            {
                maxNum++;
            }else {
                maxNum = 1;
            }
            int id = p.charAt(i) - 'a';
            dp[id] = Math.max(dp[id], maxNum);
        }

        int ans = 0;
        for(int i:dp)
        {
            ans += i;
        }

        return ans;

    }

    public int findSubstringInWraproundString3(String p) {
        if(p == null || p.length() == 0)return 0;
        //dp[i] means max number of substring (substring present in abcdefghijklmnopqrstuvwxyz )  ending with (i +'a') , where i in [0,25]
        int[] dp = new int[26];
        dp[p.charAt(0) -'a'] = 1;
        int prev = 1;
        for (int i = 1; i < p.length(); i++) {
            int diff = p.charAt(i) - p.charAt(i -1);
            if(diff < 0) diff += 26;
            int curr = 1;
            if(diff ==1)
            {
                curr += prev;
            }
            int id = p.charAt(i) - 'a';
            dp[id] = Math.max(dp[id], curr);
            prev = curr;
        }

        int ans = 0;
        for(int i:dp)
        {
            ans += i;
        }

        return ans;

    }

    public static void main(String[] args) {
        UniqueSubstringsinWraparoundString uniq = new UniqueSubstringsinWraparoundString();
        System.out.println(uniq.findSubstringInWraproundString("a"));
        System.out.println(uniq.findSubstringInWraproundString("cac"));
        System.out.println(uniq.findSubstringInWraproundString("zab"));
    }

}
