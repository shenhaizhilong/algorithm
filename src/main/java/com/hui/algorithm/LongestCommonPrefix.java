package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/31 8:46
 */
public class LongestCommonPrefix {

    /**
     *
     * 14. Longest Common Prefix
     * DescriptionHintsSubmissionsDiscussSolution
     * Write a function to find the longest common prefix string amongst an array of strings.
     *
     * If there is no common prefix, return an empty string "".
     *
     * Example 1:
     *
     * Input: ["flower","flow","flight"]
     * Output: "fl"
     * Example 2:
     *
     * Input: ["dog","racecar","car"]
     * Output: ""
     * Explanation: There is no common prefix among the input strings.
     * Note:
     *
     * All given inputs are in lowercase letters a-z.
     *
     * divide and conquer
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0)return "";
        return getLongestCommonPrefix(strs, 0, strs.length -1);

    }

    public String getLongestCommonPrefix(String[] strs, int start, int end)
    {
        if(start != end)
        {
            int mid = (start + end)>>>1;
            String leftComm = getLongestCommonPrefix(strs, start, mid);
            String rightComm = getLongestCommonPrefix(strs, mid + 1, end);
            return getCommonPrefix(leftComm, rightComm);

        }else {
            return strs[start];
        }
    }

    public  String getCommonPrefix(String left, String right)
    {
        int min = Math.min(left.length(), right.length());
        int i = 0;
        for ( i = 0; i < min; i++) {
            if(left.charAt(i) != right.charAt(i))
                break;
        }
        return left.substring(0, i);
    }


    public String longestCommonPrefix2(String[] strs)
    {
        if(strs == null || strs.length == 0)return "";
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(res) != 0)
            {
                res = res.substring(0, res.length() -1);
            }
        }

        return res;
    }
    public static void main(String[] args) {

        String[] strs1 = {"flower","flow","flight"};
        String[] strs2 = {"dog","racecar","car"};
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
//        System.out.println(longestCommonPrefix.longestCommonPrefix(strs1));
//        System.out.println(longestCommonPrefix.longestCommonPrefix(strs2));
        System.out.println(longestCommonPrefix.longestCommonPrefix2(strs2));
    }
}
