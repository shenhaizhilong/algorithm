package com.hui.algorithm;


/**
 *https://leetcode-cn.com/problems/valid-anagram/description/
 * 有效的字母异位词
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 *
 * 用桶解决此问题
 * @author: shenhaizhilong
 * @date: 2018/8/14 18:34
 */
public class Anagram {
    public static boolean isAnagram(String s, String t) {
        if(s == t)return true;
        if(s == null || t == null)return false;
        if(s.length() != t.length())return false;
        int[] buckets = new int[26];
        int indexs = 0, indext = 0;
        for (int i = 0; i < s.length(); i++) {
            indexs = s.charAt(i) - 'a';
            buckets[indexs] +=1;
            indext = t.charAt(i) - 'a';
            buckets[indext] -=1;
        }

        for (int i = 0; i < buckets.length; i++) {
            if(buckets[i] != 0)
            {
               return false;
            }

        }
        return true;

    }

    public static boolean isAnagram2(String s, String t) {
        if(s.length()!=t.length()) return false;
        int[] c = new int[26];
        int[] d = new int[26];
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        for(int i=0 ;i<sc.length ;i++){
            c[sc[i]-97]++;
            d[tc[i]-97]++;
        }
        for(int j=0 ;j<c.length ;j++){
            if(c[j]!=d[j]) return false;
        }
        return true;

    }

    public static void main(String[] args) {

        String s = "nl", t = "cx";
        System.out.println(isAnagram2(s,t));
        String a = "aabb";
        String b = "bbcc";

        System.out.println(isAnagram2(a,b));

    }
}
