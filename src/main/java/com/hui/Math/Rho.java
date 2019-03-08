package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/7 14:51
 */
public class Rho {
    public static void rho(int x1, int n)
    {
        System.out.println(x1);
        for (int i = 0; i < 20; i++) {
            x1 = (x1*x1 -1)%n;
            System.out.println(x1);
        }
    }

    public static void main(String[] args) {
        rho(2,1387);
    }
}
