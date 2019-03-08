package com.hui.String;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/15 16:01
 */
public class RemoveDuplicateLetters {


    /**
     *316. Remove Duplicate Letters
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
     *
     * Example 1:
     *
     * Input: "bcabc"
     * Output: "abc"
     * Example 2:
     *
     * Input: "cbacdcbc"
     * Output: "acdb"
     *
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {

        if(s == null || s.length() == 0)return "";

        Deque<Character> stack = new ArrayDeque<>();  // results stack
        int[] count = new int[26];              // char (i + 'a') 's frequency, i is the index
        boolean[] visited = new boolean[26]; // whether this char (i + 'a') in stack, i is the index
        char[] vals = s.toCharArray();
        for(char c: vals)
        {
            count[c - 'a']++;
        }

        for(char c: vals)
        {
          int idx = c - 'a';
          count[idx]--;
          if(visited[idx])continue; // we already visited this char
            // when stack is not empty and top element in stack larger than current char and top element 's frequency is bigger than zero
         // if future still have this top element and top element larger than current char, we can replace this top element by current char
          while (!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a'] > 0)
          {
              // top element in stack can be removed and re-added.
              visited[stack.peek() - 'a'] = false;
              stack.pop();
          }

          // add current char and set it as visited.
            stack.push(c);
          visited[idx] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pollLast());
        }
        return sb.toString();


    }

    public static void main(String[] args) {

        RemoveDuplicateLetters removeDuplicateLetters = new RemoveDuplicateLetters();
//        System.out.println(removeDuplicateLetters.removeDuplicateLetters("bbabc"));
//        System.out.println(removeDuplicateLetters.removeDuplicateLetters("ddadc"));
        System.out.println(removeDuplicateLetters.removeDuplicateLetters("cbacdcbd"));

    }
}
