package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/16 10:15
 */
public class StrongPasswordChecker {


    /**
     *
     * 420. Strong Password Checker
     * DescriptionHintsSubmissionsDiscussSolution
     * A password is considered strong if below conditions are all met:
     *
     * It has at least 6 characters and at most 20 characters.
     * It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.
     * It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
     * Write a function strongPasswordChecker(s), that takes a string s as input, and return the MINIMUM change required to make s a strong password. If s is already strong, return 0.
     *
     * Insertion, deletion or replace of any one character are all considered as one change.
     *
     * @param s
     * @return
     */
    public int strongPasswordChecker(String s) {

        boolean hasDigit = false;
        boolean hasLower = false;
        boolean hasUpper = false;
        int needDel = 0;
        int needAdd = 0;

        return 0;

    }
}
