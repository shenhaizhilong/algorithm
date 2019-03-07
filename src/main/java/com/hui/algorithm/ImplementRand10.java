package com.hui.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/14 10:31
 *
 * https://leetcode.com/problems/implement-rand10-using-rand7/solution/
 *
 */
public class ImplementRand10 {


    /**
     *
     * rejection sampling
     *
     * This solution is based upon Rejection Sampling.
     * The main idea is when you generate a number in the desired range, output that number immediately.
     * If the number is out of the desired range, reject it and re-sample again.
     * As each number in the desired range has the same probability of being chosen, a uniform distribution is produced.
     *Obviously, we have to run rand7() function at least twice, as there are not enough numbers in the range of 1 to 10.
     * By running rand7() twice, we can get integers from 1 to 49 uniformly. Why?
     * A table is used to illustrate the concept of rejection sampling. Calling rand7() twice will get us row and column index that corresponds to a unique position in the table above. Imagine that you are choosing a number randomly from the table above. If you hit a number, you return that number immediately. If you hit a * , you repeat the process again until you hit a number.
     *
     * Since 49 is not a multiple of 10, we have to use rejection sampling. Our desired range is integers from 1 to 40, which we can return the answer immediately. If not (the integer falls between 41 to 49), we reject it and repeat the whole process again.
     *
     *
     *
     * 470. Implement Rand10() Using Rand7()
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.
     *
     * Do NOT use system's Math.random().
     *
     *
     *
     * Example 1:
     *
     * Input: 1
     * Output: [7]
     * Example 2:
     *
     * Input: 2
     * Output: [8,4]
     * Example 3:
     *
     * Input: 3
     * Output: [8,1,10]
     *
     *
     * Note:
     *
     * rand7 is predefined.
     * Each testcase has one argument: n, the number of times that rand10 is called.
     *
     * @return
     */
    public int rand10() {
        int curr;
        int r,pre;
        do{
            pre = rand7();
            curr = rand7();

            r = pre + (curr -1)*7;

        }while (r > 40);
        return 1 + (r -1)%10;
    }

    public int rand102() {
        int result = 40;
        while (result >= 40) {result = 7 * (rand7()-1 ) + (rand7()-1);}
        return result % 10 + 1;
    }


    private int rand7(){
        Random random = new Random();
        return random.nextInt(7) + 1;
    }

    public static void main(String[] args) {

        ImplementRand10 rand10 = new ImplementRand10();
        int[] counter = new int[12];
        for (int i = 0; i < 12000; i++) {
           int id = rand10.rand12() - 1;
           counter[id]++;
        }
        System.out.println(Arrays.toString(counter));
    }


    public int rand12() {
        int curr;
        int r,pre;
        do{
            pre = rand7();
            curr = rand7();

            r = pre + (curr -1)*7;

        }while (r > 36);
        return 1 + (r -1)%12;
    }



}
