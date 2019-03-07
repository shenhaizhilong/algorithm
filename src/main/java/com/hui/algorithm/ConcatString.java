package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/6/23 13:47
 * https://www.jianshu.com/p/00d3fd1d9e23
 * a
 * f(1) = 0
 * aa = a + a;
 * f(2) = f(1) +1 = 1
 * aaa = aa + a;
 * f(3) = f(1) + f(2) +1 = 2
 * aaaa = aa + aa;
 * f(4) = f(2) +1 = 2
 * aaaaa = aa + aa + a;
 * f(5) = f(2) + f(3) = 1 + 2 = 3
 * aaaaaa = aaa + aaa;
 * f(6) = f(3) + 1 = 3
 * aaaaaaa = aaaa +aaa = 4
 * f(7) = f(3) + f(4) = 2 +2 =4
 * aaaaaaaa = aaaa + aaaa = 3
 *f(8) = f(4) + 1 = f(2) + 2 = f(1) + 3 = 3
 *f(100) = f(50) +1
 *        = f(25) + 2
 *        = f(12) + f(13) + 2
 * 	      = f(6) + 3 + f(6) + f(7) = 9 + 4=13
 *
 *
 */
public class ConcatString {

    public static int CountOfConcatStringOperation( int n)
    {

        if( n <= 1) return 0;
        if( n == 2) return 1;
        if( n == 3) return 2;
        if( (n &0x01) ==0)
        {
            return 1 + CountOfConcatStringOperation(n/2);
        }
        return CountOfConcatStringOperation(n/2) + CountOfConcatStringOperation(n - n/2);

    }

    public static void main(String[] args) {

            System.out.println(CountOfConcatStringOperation(100));
        for (int i = 0; i < 101; i++) {
            System.out.println("i =" + i + ", count = " + CountOfConcatStringOperation(i));
        }



        long start = System.nanoTime();
        int count = 10000;
        for (int i = 0; i < count; i++) {
            CountOfConcatStringOperation(1000000);
        }

        long end = System.nanoTime();
        System.out.println((end - start)/count);

    }
}
