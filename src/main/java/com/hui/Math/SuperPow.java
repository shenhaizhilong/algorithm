package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/13 10:08
 */
public class SuperPow {


    /**
     *
     * https://leetcode.com/problems/super-pow/discuss/84472/C++-Clean-and-Short-Solution
     *
     * @param a
     * @param n
     * @param m
     * @return
     */
    private int powMod(int a, int n, int m)
    {
        int res = 1;
        for (int i = 0; i < n; i++) {
            res = (res * (a % m))%m;
        }
        return res ;
    }

    public int superPow(int a, int[] b)
    {
       return powArray(a,b,1337, b.length -1);
    }

    public int powArray(int a, int[] b, int m, int end)
    {
        if(end < 0)return 1;
        if(a > m)
        {
            a = a % m;
        }
        int p1 = powMod(a,b[end], m);
        int p2 = powArray(a,b,m,--end);
        p2 = powMod(p2, 10, m);
        return p2*p1 % m;

    }


    public int superPow2(int a, int[] b) {
        if(a % 1337 == 0)return 0;
        int p = 0;
        for (int i: b)
        {
            //Euler's theorem
            p = (p*10 +i)%1140;
        }
        if(p == 0) p+= 1140;
        return powerMod(a, p, 1337);
    }


    /**
     *
     * https://leetcode.com/problems/super-pow/discuss/84479/Java-4ms-solution-using-the-remainder-repeat-pattern
     *https://leetcode.com/problems/super-pow/discuss/84466/Math-solusion-based-on-Euler's-theorem-power-called-only-ONCE-C++Java1-line-Python
     * @param a
     * @param n
     * @param mod
     * @return
     */
    public int powerMod(int a, int n, int mod)
    {
        int res = 1;
        a = a % mod;
        while (n != 0)
        {
            if((n &0x01) !=0) res = res* a % mod;

            //In addition, if a > 1337, we can let a = a mod 1337.
            //Because if we let a = (1337x + c) where c = a mod 1337,
            //(1337x + c)(1337x + c)(1337x + c)...(1337x + c) mod 1337 == ccc...c mod 1337.
            a = a*a % mod;
            n >>>=1;

        }
        return res;
    }


    public static void main(String[] args) {

        SuperPow superPow = new SuperPow();
      //  System.out.println(superPow.powMod(3,400, 10));
        System.out.println(superPow.superPow(2,new int[]{1,0,1,1,1,1}));
        System.out.println(superPow.superPow2(2,new int[]{1,0,1,1,1,1}));
    }
}
