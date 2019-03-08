package com.hui.Random;

import java.util.Random;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/11 16:52
 */
public class MyRandom extends Random {

    /**
     * return a int number from [a,b]
     * @param a
     * @param b
     * @return
     */
    public int nextInt(int a, int b)
    {
        return nextInt(b -a +1) +a;
    }

}
