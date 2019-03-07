package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/20 12:20
 */
public class SplitArrayintoFibonacciSequence {


    /**
     *
     *
     * @param S
     * @return
     */
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> list = new ArrayList<>();
        if(S == null || S.length() < 3)return list;
        int len = S.length();
        for (int i = 1; i <= Math.min(len/2, 11); i++) {
            for (int j = 1; j <=Math.min(len - i - Math.max(i,j), 11) ; j++) {
                // i means the first number's length, j means the second number's length
                if(isValid(S, i, j, list)){
                    return list;
                }else {
                    list.clear();
                }
            }
        }
        return list;
    }

    private boolean isValid(String num, int i, int j, List<Integer> list)
    {
        if(num.charAt(0) == '0' && i > 1)return false; // first number have leading zeros
        if(num.charAt(i) == '0' && j > 1)return false; // second number have leading zeros.
        long a = Long.parseLong(num.substring(0,i));
        long b = Long.parseLong(num.substring(i,i + j));
        // a + b = c
        // b + c = d
        //  a = b, b = c

        list.add((int) a);
        list.add((int) b);
        String sum;
        for (int start = i + j; start < num.length(); start += sum.length() ) {
            long c = a + b;
            if(c > Integer.MAX_VALUE)return false;
            list.add((int)c);
            a = b;
            b = c;
            sum = String.valueOf(c);
            if(!num.startsWith(sum, start))return false;
        }

        return true;

    }

    public static void main(String[] args) {

        SplitArrayintoFibonacciSequence splitArrayintoFibonacciSequence = new SplitArrayintoFibonacciSequence();
//        System.out.println(splitArrayintoFibonacciSequence.splitIntoFibonacci("123456579"));
//        System.out.println(splitArrayintoFibonacciSequence.splitIntoFibonacci("11235813"));
//        System.out.println(splitArrayintoFibonacciSequence.splitIntoFibonacci("112358130"));
//        System.out.println(splitArrayintoFibonacciSequence.splitIntoFibonacci("0123"));
//        System.out.println(splitArrayintoFibonacciSequence.splitIntoFibonacci("1101111"));
        System.out.println(splitArrayintoFibonacciSequence.splitIntoFibonacci("74912134825162255812723932620170946950766784234934"));
    }

}
