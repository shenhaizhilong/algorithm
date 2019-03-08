package com.hui.BitOperation;

/**
 * @author: shenhaizhilong
 * @date: 2018/6/27 19:00
 */
public class BItCount {



    /**
     * https://stackoverflow.com/questions/22081738/how-does-this-algorithm-to-count-the-number-of-set-bits-in-a-32-bit-integer-work
     * https://codingforspeed.com/a-faster-approach-to-count-set-bits-in-a-32-bit-integer/
     *
     * @param n
     * @return
     */

    public static int bitCount(int n) {


        if (n == 0) return 0;
        int[] masks = {0x55555555, 0x33333333, 0x0f0f0f0f, 0x00ff00ff, 0x0000ffff};
        n = (n & masks[0]) + ((n >>> 1) & masks[0]);
        n = (n & masks[1]) + ((n >>> 2) & masks[1]);
        n = (n & masks[2]) + ((n >>> 4) & masks[2]);
        n = (n & masks[3]) + ((n >>> 8) & masks[3]);
        n = (n & masks[4]) + ((n >>> 16) & masks[4]);
        return n & 0x3f;
    }

    public static int bitCount2(int n) {

        int r = 0;
        while (n != 0)
        {
            r++;
            n = n&(n-1);
        }
        return r;
    }

    public static int bitCount2(long n) {

        int r = 0;
        while (n != 0)
        {
            r++;
            n = n&(n-1L);
        }
        return r;
    }

    public static void main(String[] args) {

//        System.out.println((-1>>31));
//        System.out.println((-1>>>31));
//        System.out.println( (1>>31) | (-1>>>31));
//        System.out.println( (-1>>31) | (1>>>31));
//
//        System.out.println(bitCount(-1));
//        System.out.println(printBits((2<<-3)));
//        System.out.println(printBits((2>>>-1)));
//        for (int i = 0; i < 10; i++) {
//            System.out.println(printBits(i));
//            System.out.println(printBits((i>>>-2)));
//            System.out.println("***********************************");
//        }

//        int r = 0;
//        for (int i = 0; i < 10; i++) {
//           r = Integer.rotateLeft(i, 2);
//            System.out.println(printBits(i));
//            System.out.println(printBits((i >>> -2)));
//            System.out.println(r);
//            System.out.println(printBits(r));
//            System.out.println("***********************************");
//        }
//

//        for (int i = 0; i < 10; i++) {
//            System.out.println(tableSizeFor(i));
//        }
//
//
//        System.out.println(tableSizeFor(65535));
//        System.out.println(Integer.highestOneBit(10));
//        System.out.println(floorPowerOfTwo(10));

//        for (int i = 0; i < Integer.MAX_VALUE; i++) {
//            if((bitCount(i) &0x01) !=evenOrOdd(i))
//            {
//                System.out.println(i);
//            }
//        }

//        for (int i = 0; i < Integer.MAX_VALUE; i++) {
//            if(Integer.reverse(i) != revserse(i))
//            {
//                System.out.println(i);
//            }
//        }

//        for (int i = Integer.MIN_VALUE; i <Integer.MAX_VALUE; i++) {
//          if(bitCount(i) != bitCount2(i))
//          {
//              System.out.println(i + "bitCount2:" + bitCount2(i) + "bitCount1:" + bitCount(i));
//          }
//        }

        System.out.println(bitCount2(Long.MIN_VALUE) == Long.bitCount(Long.MIN_VALUE));
        System.out.println(bitCount2(Long.MAX_VALUE) == Long.bitCount(Long.MAX_VALUE));
        int i = 10;
        System.out.println(~i);
        System.out.println((i ^ -1));
        System.out.println(BitUtil.getBitsStr(-1));
        System.out.println(BitUtil.getBitsStr((-1)<<5));
    }
}
