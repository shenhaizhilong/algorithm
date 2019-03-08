package com.hui.String;

import java.util.*;

/**
 *
 * 784. Letter Case Permutation
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.
 *
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * Input: S = "3z4"
 * Output: ["3z4", "3Z4"]
 *
 * Input: S = "12345"
 * Output: ["12345"]
 * Note:
 *
 * S will be a string with length at most 12.
 * S will consist only of letters or digits.
 *
 *
 *
 * 0000 | abcd
 * 0001 | abcD
 * 0010 | abCd
 * 0011 | abCD
 * 0100 | aBcd
 * 0101 | aBcD
 * 0110 | aBCd
 * 0111 | aBCD
 * 1000 | Abcd
 * 1001 | AbcD
 * 1010 | AbCd
 * 1011 | AbCD
 * ...
 * 1111 | WORD
 * @author: shenhaizhilong
 * @date: 2018/8/19 18:11
 */
public class LetterCasePermutation {
    public static List<String> letterCasePermutation(String S) {
        List<String> list = new ArrayList<>();
        
        if(S == null)return list;
        if(S == ""){
            list.add("");
            return list;
        }
        String lowerStr = S.toLowerCase();
        char[] res = lowerStr.toCharArray();
        int len = (int) Math.pow(2,S.length());
        HashSet<String> set = new HashSet<>(len);

        for (int i = 0; i < len; i++) {
           String temp = Integer.toBinaryString(i);
            for (int j = temp.length() -1;j>=0; j--) {
                if(temp.charAt(temp.length() -1 -j) =='1')
                {
                    int index = res.length - 1 -j;
                    if(Character.isLetter(lowerStr.charAt(index))) {
                        res[index] = (char) (lowerStr.charAt(index) - 32);
                    }

                }
            }

            set.add(new String(res));
            res = lowerStr.toCharArray();



        }

        for (String w :
                set) {
            list.add(w);
        }

        return list;
    }



    public static List<String> letterCasePermutation2(String S) {
        List<String> res = new ArrayList();
        dfs(S.toCharArray(), 0, res);
        return res;
    }

    private static void dfs (char[] ss, int index, List<String> res) {
        if (index == ss.length) {
            res.add(new String(ss));
            return;
        }
        if (Character.isDigit(ss[index])) dfs(ss, index + 1, res);
        else if (ss[index] >= 'a') {
            dfs(ss, index + 1, res);
            char ori = ss[index];
            ss[index] = (char)(ori - 'a' + 'A');
            dfs(ss, index + 1, res);
            ss[index] = ori;
        } else {
            dfs(ss, index + 1, res);
            char ori = ss[index];
            ss[index] = (char)(ori - 'A' + 'a');
            dfs(ss, index + 1, res);
            ss[index] = ori;
        }
    }


    /**
     *
     * https://zxi.mytechroad.com/blog/searching/leetcode-784-letter-case-permutation/
     * @param S
     * @return
     */
    public static List<String> letterCasePermutation3(String S) {
        List<String> ans = new ArrayList<>();
        dfs3(S.toCharArray(), 0, ans);
        return ans;
    }

    /**
     *
     * Image there is a binary tree, we start from the dummy root, and the left child node means choice 1(append a lower case),
     * and right child node is choice 2(upper case). Hence if String here is abc, we have a tree looks like this.
     * Obviously our goal here will be printing out all leaf nodes, which we can accomplished by using DFS with backTracking or BFS.
     *                        dummy root
     *                      /          \
     *                    /             \
     *                  /                \
     *                a                   A
     *             /     \              /    \
     *           /        \           /       \
     *         ab          aB        Ab       AB
     *       /   \       /   \     /   \    /   \
     *      abc  abC    aBc  aBC  Abc  AbC ABc  ABC
     *
     *
     * 65^32 = 97
     * 97^32 = 65
     * 'a'^32 = 'A'
     * 'A'^32 = 'a'
     * @param S
     * @param i
     * @param ans
     */
    private static void dfs3(char[] S, int i, List<String> ans) {

        // if i == S.length 到达叶子节点
        if (i == S.length) {
            System.out.println(new String(S).substring(0,i));
            ans.add(new String(S));
            return;
        }
        //深度优先，下一个节点
        System.out.println(new String(S).substring(0,i));
        dfs3(S, i + 1, ans);
        if (!Character.isLetter(S[i])) return;
        S[i] ^= 32;
        System.out.println(new String(S).substring(0,i));
        dfs3(S, i + 1, ans);
        S[i] ^= 32;

    }


    /**
     *
     *https://leetcode.com/problems/letter-case-permutation/discuss/115485/Java-Easy-BFS-DFS-solution-with-explanation
     * When I saw a problem, my first step is to draw a figure. See the figure below:
     * abc
     * abc Abc 0
     * abc aBc Abc ABc 1
     * abc abC aBc aBC Abc AbC ABc ABC 2
     *
     * There we go! Is that a typical BFS/DFS problem? Yes, you are right!
     * Be careful to check whether a character is a digit or a letter(lower case or upper case).
     * BFS Solution:
     * @param S
     * @return
     */
    public static List<String> letterCasePermutation4(String S) {
        if (S == null) {
            return new LinkedList<>();
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(S);

        for (int i = 0; i < S.length(); i++) {
            if (Character.isDigit(S.charAt(i))) continue;
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String cur = queue.poll();
                char[] chs = cur.toCharArray();

                chs[i] = Character.toUpperCase(chs[i]);
                queue.offer(String.valueOf(chs));

                chs[i] = Character.toLowerCase(chs[i]);
                queue.offer(String.valueOf(chs));
            }
        }

        return new LinkedList<>(queue);
    }

    public static void main(String[] args) {
       List<String> list = letterCasePermutation3("abc");
        System.out.println(list);
        System.out.println(list.size());
    }
}
