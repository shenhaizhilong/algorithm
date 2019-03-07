package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/16 17:19
 *
 *
 *
 * 664. Strange Printer
 * DescriptionHintsSubmissionsDiscussSolution
 * There is a strange printer with the following two special requirements:
 *
 * The printer can only print a sequence of the same character each time.
 * At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
 * Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.
 *
 * Example 1:
 * Input: "aaabbb"
 * Output: 2
 * Explanation: Print "aaa" first and then print "bbb".
 * Example 2:
 * Input: "aba"
 * Output: 2
 * Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
 * Hint: Length of the given string will not exceed 100.
 *
 *
 */
public class StrangePrinter {

    private static int[] primes =  {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    public int strangePrinter(String s) {
        if(s == null)return 0;
        if(s.length() < 2)return s.length();
        char[] chars = s.toCharArray();
        int end = 0;
        for (int i = 1; i < chars.length; i++) {
            if(chars[i] == chars[end])continue;
            chars[++end] = chars[i];
        }
        int Len = end +1;
        int[][] memo = new int[Len][Len];
        return dfs(chars,0, end,memo);

    }

    private int dfs(char[] chars, int start, int end, int[][] cache)
    {
        if(cache[start][end] != 0)return cache[start][end];
        if(start > end)return 0;
        if(start == end)return 1;
        int len = end - start +1;
        // avoid overflow
        if(len <= 9)
        {
            long product = 1;
            boolean noDup = true;
            for (int i = start; i <=end && noDup; i++) {
               int idx = chars[i] - 'a';
               if (product % primes[idx] == 0)
               {
                   noDup = false;
                   break;
               }
               product *= primes[idx];
            }
            if(noDup)
            {
                cache[start][end] = len;
                return len;
            }
        }
        int ans = 1 + dfs(chars, start, end -1, cache);
        for (int i = start; i < end; i++) {
            if(chars[i] == chars[end])
            {
                int left = dfs(chars, start, i, cache);
                int right = dfs(chars, i + 1, end -1, cache);
                ans = Math.min(ans, left + right);
            }
        }
        cache[start][end] = ans;
        return ans;

    }

    public static void main(String[] args) {

        StrangePrinter strangePrinter = new StrangePrinter();
//        System.out.println(strangePrinter.strangePrinter("abcdefg"));
//        System.out.println(strangePrinter.strangePrinter("abcd"));
//        System.out.println(strangePrinter.strangePrinter("aacdd"));
//        System.out.println(strangePrinter.strangePrinter("aaabbb"));
//        System.out.println(strangePrinter.strangePrinter("aba"));
        System.out.println(strangePrinter.strangePrinter("abcabc"));
    }

}
