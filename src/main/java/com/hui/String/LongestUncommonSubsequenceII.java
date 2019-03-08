package com.hui.String;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/2 11:07
 */
public class LongestUncommonSubsequenceII {


    public static int findLUSlength(String[] strs) {

      int ans = -1;
      int len = strs.length;
        for (int i = 0; i < len; i++) {
            if(strs[i].length() < ans)continue;
            int j = 0;
            while (j < len)
            {
                if(i != j && isSubsequence(strs[i], strs[j]))break;
                j++;
            }
            if(j == len){
                ans = strs[i].length();
            }
        }
        return ans;
    }


    public static boolean isSubsequence(String a, String b){
        int i=0, j=0;
        while(i<a.length() && j<b.length()) if (a.charAt(i)==b.charAt(j++)) i++;
        return i==a.length();
    }

    public static void main(String[] args) {

//        String[] strs = {"aba", "cdc", "eae"};
//        System.out.println(findLUSlength(strs));
//        String[] strs2 = {"aaa","aaa","aa"};
//        System.out.println(findLUSlength(strs2));
       String[] strs3 = {"aabbcc", "aabbcc","cb","abc"};
        System.out.println(findLUSlength(strs3));
    }
}
