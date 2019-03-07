package com.hui.algorithm;

/**
 * when mid = (a*a)/2, mid*mid >=a*a, when mid >=2
 * @author: shenhaizhilong
 * @date: 2018/6/29 12:08
 */
public class IsPerfectSquare {
    public static boolean isPerfectSquare(int n)
    {

        if(n ==0)return true;
        if(n ==1)return true;
        long left=0;
        long right = n;
        long mid;
        long t;
        while (left <= right)
        {
            mid = (left + right)>>>1;
            t = mid*mid;
            if(t == n)return true;
            if(t < n) left = mid +1;
            if(t > n) right = mid -1;

        }
        return false;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            if(isPerfectSquare(i))
            {
                System.out.println(i);
            }
        }
    }
}
