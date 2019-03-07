package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/15 12:53
 *
 *
 * 395. Longest Substring with At Least K Repeating Characters
 * DescriptionHintsSubmissionsDiscussSolution
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
 *
 * Example 1:
 *
 * Input:
 * s = "aaabb", k = 3
 *
 * Output:
 * 3
 *
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 *
 * Input:
 * s = "ababbc", k = 2
 *
 * Output:
 * 5
 *
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 *
 */
public class LongestSubstringwithAtLeastKRepeatingCharacters {
    int count = 0;
    public int longestSubstring(String s, int k) {
        if(s == null || s.length() < k)return 0;
        int ans = helper(s.toCharArray(), 0, s.length() -1, k);
      //  System.out.println("run:" + count);
        return ans;
    }

    private int helper(char[] chars, int L, int R, int k)
    {
        //count++;
        if(R - L +1 < k)return 0;
        int[] counter = new int[26];
        for (int i = L; i <=R; i++) {
            int idx = chars[i] - 'a';
            counter[idx]++;
        }

        boolean pass = true;
        for (int i = 0; i < 26; i++) {
            if(counter[i] > 0 && counter[i] < k)
            {
                pass = false;
                break;
            }
        }
        if(pass)return R - L +1;
        int left = L;
        int ans = 0;
        for (int i = left; i <= R; i++) {
            // aaabcccdeeeeee, k = 3; split point is index 3, 7; split range(0,2) , range(4,6), range(8---end)
            if(counter[chars[i] - 'a'] > 0 && counter[chars[i] - 'a']< k)
            {
                if(i - left > ans) // need to calculate range length bigger than the Max ans
                    ans = Math.max(ans, helper(chars, left, i -1, k));
                left = i +1;
            }
        }

        return Math.max(ans, helper(chars, left, R, k));

    }

    public static void main(String[] args) {

        LongestSubstringwithAtLeastKRepeatingCharacters repeatingCharacters = new LongestSubstringwithAtLeastKRepeatingCharacters();
        System.out.println(repeatingCharacters.longestSubstring("aaabcccdeeeee",3));
        System.out.println(repeatingCharacters.longestSubstring("aaablfjajfpajifaojiajfjlajkklllllllllllllb",3));
        System.out.println(repeatingCharacters.longestSubstring("aaablfjajfpajifaddddddddddddddddddddddddddjiajffaaaffafafkafkakfkfklklkjlajkklllllllllllllb",2));

    }
}
