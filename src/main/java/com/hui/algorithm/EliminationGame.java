package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/27 22:44
 *
 *390. Elimination Game
 * DescriptionHintsSubmissionsDiscussSolution
 * There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.
 *
 * Repeat the previous step again, but this time from right to left, remove the right most number and every other number from the remaining numbers.
 *
 * We keep repeating the steps again, alternating left to right and right to left, until a single number remains.
 *
 * Find the last number that remains starting with a list of length n.
 *
 * Example:
 *
 * Input:
 * n = 9,
 * 1 2 3 4 5 6 7 8 9
 * 2 4 6 8
 * 2 6
 * 6
 *
 * Output:
 * 6
 *
 */
public class EliminationGame {

    // f(n,left) starts from left
    // f(n,right) starts from right
    // f(2n+1,left) == f(2n, left), since first time, all odd numbers are eliminated.
    // f(n,left) = 2*f(n/2, right)
    // f(1,2,3,...n,left) = r; replace newValue = n +1 - oldValue;
    // f(n, n -1, n -2, ...1, left) = n +1 - r == f(1,2,3,.. n, right)== f(n,right)
    // so f(n,left) + f(n,right) = n +1 - r + r == n +1;
    // f(n,left) + f(n, right) = n +1; then f(n,right) = n +1 - f(n,left)
    // after first time: all the odd numbers are eliminated, remaining number are even numbers : 2*(1, 2, 3, ... n/2)
    // f(n,left) = 2*f(n/2, right) = 2(n/2 +1 - f(n/2,left))
    // base condition f(1,left) == 1 == f(1,right)
    // so f(n,left) = n == ? 1: 2*(n/2 +1 - f(n/2,left))
    public int lastRemaining(int n) {
        if(n == 1)return 1;
        else if(n <= 5)return 2;
        return  2*(n/2 +1 - lastRemaining(n/2));
    }
}
