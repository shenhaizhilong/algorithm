package com.hui.DynamicPrograming;

import java.util.HashMap;

/**
 *
 * https://leetcode.com/problems/isomorphic-strings/description/
 *  Isomorphic Strings
 *
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 *
 * @author: shenhaizhilong
 * @date: 2018/8/15 13:19
 */
public class Isomorphic {

    public boolean isIsomorphic(String s, String t) {
        return helper(s,t) && helper(t,s);
    }
    private boolean helper(String s, String t) {
        if(s == null && t == null)return true;
        if(s == null || t == null)return false;
        if(s.length() <=1 && t.length() <=1)return true;
        if(s.length() != t.length())return false;
        HashMap<Character,Character> map = new HashMap<>();

        char left, right,mappedChar;
        for (int i = 0; i < s.length(); i++) {
             left = s.charAt(i);
             right = t.charAt(i);
             if(!map.containsKey(left))
             {
                 map.put(left,right);
             }else {
                 mappedChar = map.get(left).charValue();
                 if(mappedChar != right)
                 {
                     return false;
                 }

             }

        }

        return true;
    }


    /**
     * 另外的解
     * @param sString
     * @param tString
     * @return
     */
    public boolean isIsomorphic2(String sString, String tString) {

        char[] s = sString.toCharArray();
        char[] t = tString.toCharArray();

        int length = s.length;
        if(length != t.length) return false;

        char[] sm = new char[256];
        char[] tm = new char[256];

        for(int i=0; i<length; i++){
            char sc = s[i];
            char tc = t[i];
            if(sm[sc] == 0 && tm[tc] == 0){
                sm[sc] = tc;
                tm[tc] = sc;
            }else{
                if(sm[sc] != tc || tm[tc] != sc){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Isomorphic isomorphic = new Isomorphic();
        System.out.println(isomorphic.isIsomorphic("egg","add"));
        System.out.println(isomorphic.isIsomorphic("foo","bar"));
        System.out.println(isomorphic.isIsomorphic("paper","title"));
        System.out.println(isomorphic.isIsomorphic("aa","ab"));
    }
}
