package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/23 0:18
 *
 * 925. Long Pressed Name
 * DescriptionHintsSubmissionsDiscussSolution
 * Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.
 *
 * You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.
 *
 *
 *
 * Example 1:
 *
 * Input: name = "alex", typed = "aaleex"
 * Output: true
 * Explanation: 'a' and 'e' in 'alex' were long pressed.
 * Example 2:
 *
 * Input: name = "saeed", typed = "ssaaedd"
 * Output: false
 * Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
 * Example 3:
 *
 * Input: name = "leelee", typed = "lleeelee"
 * Output: true
 * Example 4:
 *
 * Input: name = "laiden", typed = "laiden"
 * Output: true
 * Explanation: It's not necessary to long press any character.
 *
 *
 * Note:
 *
 * name.length <= 1000
 * typed.length <= 1000
 * The characters of name and typed are lowercase letters.
 *
 */
public class LongPressedName {

    public boolean isLongPressedName(String name, String typed) {
        if(name.length() > typed.length())return false;
        int i = 0;
        for (int j = 0; j < typed.length(); j++) {

            // if match, i++, j++
            if( i < name.length() && name.charAt(i) == typed.charAt(j))
            {
                i++;
            }else if (j == 0 || typed.charAt(j) != typed.charAt(j -1)){
                return false;
            }
        }

        return i == name.length();
    }

    public boolean isLongPressedName2(String name, String typed) {
        int lenN = name.length();
        int lenT = typed.length();
        if (lenN > lenT) return false;
        int i = 0;
        int j = 0;

        while (j < lenT)
        {
            if( i < lenN && name.charAt(i) == typed.charAt(j)){
                i++;
                j++;
            }else if(i > 0 && name.charAt(i -1) == typed.charAt(j))
            {
                j++;
            }else {
                return false;
            }
        }
        return i == lenN;
    }
}
