package com.hui.Math;

/**
 *
 * https://en.wikipedia.org/wiki/Perfect_number
 *
 * @author: shenhaizhilong
 * @date: 2018/8/22 8:42
 */
public class PerfectNumber{

        public int pn(int p) {
            return (1 << (p - 1)) * ((1 << p) - 1);
        }

        public boolean checkPerfectNumber(int num) {
            if(num <=0)return false;
            if((num & 0x01) == 1)return false;
            int[] primes=new int[]{2,3,5,7,13};
            for (int prime: primes) {
                if (pn(prime) == num)
                    return true;
            }
            return false;
        }


    /**
     *
     * https://oeis.org/A000396
     *
     * 		Perfect numbers n: n is equal to the sum of the proper divisors of n.
     * (Formerly M4186 N1744)		394
     * 6, 28, 496, 8128, 33550336, 8589869056, 137438691328, 2305843008139952128,
     * 2658455991569831744654692615953842176, 191561942608236107294793378084303638130997321548169216
     * @param num
     * @return
     */
    public boolean checkPerfectNumber2(int num) {
            if((num &0x01) ==1)return false;
            if(num == 6 || num == 28 || num == 496 ||
                    num == 8128 || num == 33550336)
                return true;
            return false;
    }
    public static void main(String[] args) {
        PerfectNumber perfectNumber = new PerfectNumber();
        int[] primes=new int[]{2,3,5,7,13};
        for (int i = 0; i < primes.length; i++) {
            System.out.println(perfectNumber.pn(primes[i]));
        }

    }

}
