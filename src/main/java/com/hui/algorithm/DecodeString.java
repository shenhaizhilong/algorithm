package com.hui.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/16 10:45
 */
public class DecodeString {


    /**
     * 394. Decode String
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an encoded string, return it's decoded string.
     *
     * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
     *
     * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
     *
     * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
     *
     * Examples:
     *
     * s = "3[a]2[bc]", return "aaabcbc".
     * s = "3[a2[c]]", return "accaccacc".
     * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
     * @param s
     * @return
     */
    public String decodeString(String s) {
        if(s==null||s.length()==0)return "";
        Deque<Integer> repeatNum = new ArrayDeque<>();
        Deque<String>  strStack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        char[] vals = s.toCharArray();
        int OpenBrackets = 0;
        for (int i = 0; i < vals.length; ) {
            if(OpenBrackets <1  && Character.isLetter(vals[i]))
            {
                sb.append(vals[i]);
                i++;
            }else if(OpenBrackets >0  && Character.isLetter(vals[i]))
            {
                StringBuilder tempSb = new StringBuilder();
                while (i < vals.length && Character.isLetter(vals[i]))
                {
                    tempSb.append(vals[i++]);
                }
                String temp = strStack.pop();
                temp += tempSb.toString();
                strStack.push(temp);

            } else if(Character.isDigit(vals[i]))
            {
                int n = 0;
                while (i < vals.length && Character.isDigit(vals[i]))
                {
                    n = n*10 + vals[i] - '0';
                    i++;
                }
                repeatNum.push(n);
            }else if(vals[i] == '[')
            {
                OpenBrackets++;
                i++;
                StringBuilder tempSb = new StringBuilder();
                while (i < vals.length && Character.isLetter(vals[i]))
                {
                    tempSb.append(vals[i++]);
                }
                strStack.push(tempSb.toString());
            }else if(vals[i] == ']')
            {
                OpenBrackets--;
                i++;
                int n = repeatNum.pop();
                StringBuilder tempSb = new StringBuilder();
                String tempStr = strStack.pop();
                while (n > 0)
                {
                    tempSb.append(tempStr);
                    n--;
                }
                if(OpenBrackets > 0)
                {
                    String temp = strStack.pop();
                    temp += tempSb.toString();
                    strStack.push(temp);
                }else {
                    sb.append(tempSb.toString());
                }
            }else {
                i++;
            }
        }

        return sb.toString();


    }

    public String decodeString2(String s)
    {
        if( s == null || s.length() == 0)return s;
        return dfs(s, 0);
    }

    private String dfs(String s, int idx)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = idx; i < s.length(); i++) {

            if(s.charAt(i) <= '9' && s.charAt(i) >= '0')
            {

                // find the repeat number
                int repeatTimes = 0;
                while (s.charAt(i) <= '9' && s.charAt(i) >= '0')
                {
                    repeatTimes = repeatTimes*10 + s.charAt(i) - '0';
                    i++;
                }

                // i++ to skip '['
                i++;
                String repeatStr = dfs(s, i);
                while (repeatTimes > 0)
                {
                    sb.append(repeatStr);
                    repeatTimes--;
                }

                // skip all the chars that have already been handled.
                int left = 1;
                while (left != 0)
                {
                    i++;
                    if(s.charAt(i) == '[')left++;
                    else if(s.charAt(i) == ']')left--;
                }


            }else if(s.charAt(i) == ']')
            {
                return sb.toString();
            }else {
                sb.append(s.charAt(i));
            }

        }
        return sb.toString();
    }


    public String decodeString3(String s)
    {
        if( s == null || s.length() == 0)return s;
        return dfs3(s, new int[]{0});
    }

    private String dfs3(String s, int[] idx)
    {
        StringBuilder sb = new StringBuilder();
        while ( idx[0] < s.length()) {

            if(s.charAt(idx[0]) <= '9' && s.charAt(idx[0]) >= '0')
            {

                // find the repeat number
                int repeatTimes = 0;
                while (s.charAt(idx[0]) <= '9' && s.charAt(idx[0]) >= '0')
                {
                    repeatTimes = repeatTimes*10 + s.charAt(idx[0]) - '0';
                    idx[0]++;
                }

                // i++ to skip '['
                idx[0]++;
                String repeatStr = dfs3(s, idx);
                while (repeatTimes > 0)
                {
                    sb.append(repeatStr);
                    repeatTimes--;
                }


            }else if(s.charAt(idx[0]) == ']')
            {
                idx[0]++;
                return sb.toString();
            }else {
                sb.append(s.charAt(idx[0]++));
            }

        }
        return sb.toString();
    }


    public static void main(String[] args) {


        DecodeString decodeString = new DecodeString();
//        System.out.println(decodeString.decodeString("3[a]2[bc]"));
//        System.out.println(decodeString.decodeString("3[a2[c]]"));
//        System.out.println(decodeString.decodeString("2[abc]3[cd]ef"));
      //  System.out.println(decodeString.decodeString("2[b4[F]c]"));

        System.out.println(decodeString.decodeString3("3[a]2[bc]"));
        System.out.println(decodeString.decodeString3("3[a2[c]]"));
        System.out.println(decodeString.decodeString3("2[abc]3[cd]ef"));
        System.out.println(decodeString.decodeString3("2[b4[F]c]"));
    }
}
