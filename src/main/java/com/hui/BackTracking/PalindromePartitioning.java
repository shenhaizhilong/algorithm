package com.hui.BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/9 14:17
 */
public class PalindromePartitioning {

    /**
     *
     * 131. Palindrome Partitioning
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     *
     * Return all possible palindrome partitioning of s.
     *
     * Example:
     *
     * Input: "aab"
     * Output:
     * [
     *   ["aa","b"],
     *   ["a","a","b"]
     * ]
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> lists = new ArrayList<>();
        backTracking(lists, new ArrayList<>(), s, 0);
        return lists;

    }

    private boolean isPalindrome(String data, int start, int end)
    {
        while (start < end)
        {
            if(data.charAt(start++) != data.charAt(end--))
            {
                return false;
            }
        }
        return true;
    }


    private void backTracking(List<List<String>> lists, List<String> tempList, String s, int start)
    {

        if(start == s.length())
        {
            lists.add(new ArrayList<>(tempList));
        }else {
            for (int i = start; i < s.length(); i++) {
                if(isPalindrome(s,start,i))
                {
                    tempList.add(s.substring(start,i + 1));
                    backTracking(lists,tempList,s, i + 1);
                    tempList.remove(tempList.size() -1);
                }
            }
        }

    }

}
