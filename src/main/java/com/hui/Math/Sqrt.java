package com.hui.Math;

/**

 *
 * @author: shenhaizhilong
 * @date: 2018/7/2 11:23
 */
public class Sqrt {
    public static int mySqrt(int x) {

        if(x <0) throw new IllegalArgumentException("x must be positive");
        return (int)Math.sqrt(x);

    }

   public static int  newtonSqrt(int n)
    {
        if(n ==0)return 0;
        if(n < 4)return 1;

        double x = 2.0d;
        while (Math.abs(x*x- n) >1e-2)
        {
            x = (x + n/x)/2;
        }
        return (int)x;
    }

    public static void main(String[] args) {

//        for (int i = 0; i <90; i++) {
//            int s = (int)Math.sqrt(i);
//            int m = mySqrt(i);
//            if(s != m)
//            {
//                System.out.println("i=" + i +", " + m);
//            }
//        }

//        System.out.println(mySqrt(65537*2));
//        //System.out.println(65537d*65537d);
//
//        for (int i = 0; i < 100; i++) {
//            System.out.println(newtonSqrt(i));
//        }


        int count = 10000000;
        long t1 = System.nanoTime();
        for (int i = 0; i < count; i++) {
            mySqrt(i);
        }
        long t2 = System.nanoTime();
        for (int i = 0; i < count; i++) {
            newtonSqrt(i);
        }
        long t3 = System.nanoTime();

        System.out.println((t2-t1));

        System.out.println((t3-t2) );


    }
}
