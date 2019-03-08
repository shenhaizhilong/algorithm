package com.hui.Queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/1 10:08
 */
public class BackspaceCompare {

    /**
     *Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
     *
     * Example 1:
     *
     * Input: S = "ab#c", T = "ad#c"
     * Output: true
     * Explanation: Both S and T become "ac".
     * Example 2:
     *
     * Input: S = "ab##", T = "c#d#"
     * Output: true
     * Explanation: Both S and T become "".
     * Example 3:
     *
     * Input: S = "a##c", T = "#a#c"
     * Output: true
     * Explanation: Both S and T become "c".
     * Example 4:
     *
     * Input: S = "a#c", T = "b"
     * Output: false
     * Explanation: S becomes "c" while T becomes "b".
     * Note:
     *
     * 1 <= S.length <= 200
     * 1 <= T.length <= 200
     * S and T only contain lowercase letters and '#' characters.
     * Follow up:
     *
     * Can you solve it in O(N) time and O(1) space?
     *
     * @param S
     * @param T
     * @return
     */
    public static boolean backspaceCompare(String S, String T) {
        return removeBackspace(S.toCharArray()).equals(removeBackspace(T.toCharArray()));
    }

    private static String removeBackspace(char[] data)
    {
        Deque<Character> deque = new ArrayDeque<>();
        for(char c: data)
        {
            if(c != '#')
            {
                deque.push(c);
            }else if(!deque.isEmpty())
            {
                deque.pop();
            }
        }
        return String.valueOf(deque);
    }

    public static boolean backspaceCompare2(String S, String T) {

        int i = S.length() -1;
        int j = T.length() -1;
        int skipS = 0;
        int skipT = 0;
        while ( i >=0 || j >=0)
        {
            // find the next char's index from S
            while (i >= 0)
            {
                if(S.charAt(i) == '#'){skipS++; i--;}
                else if(skipS > 0){skipS--; i--;}
                else break;
            }

            //find the next char's index from T
            while (j >= 0)
            {
                if(T.charAt(j) == '#'){skipT++; j--;}
                else if(skipT > 0){skipT--; j--;}
                else break;
            }

            //if two char are different
            if(i >=0 && j >=0 && S.charAt(i) != T.charAt(j))return false;

            //a char vs nothing
            if((i >=0) != (j >= 0))
                return false;
            i--;j--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(backspaceCompare2("a##ccc", "#a#ccc"));
    }
}
