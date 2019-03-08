package com.hui.Random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/17 9:34
 */
public class RandomFlipMatrix {

    private Random random;
    private int R,C;
    private int size;
    private Map<Integer, Integer> map;

    public RandomFlipMatrix(int n_rows, int n_cols) {

        R = n_rows;
        C = n_cols;
        size = n_cols*n_rows;
        random = new Random();
        map = new HashMap<>();
    }

    /**
     *
     * using modern method of the Fisher–Yates shuffle.
     *
     * Please read how the original algorithm works first: Examples
     * https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#Examples
     *
     * So, the steps are:
     *
     * generate random from 0 to n :  random(n) = = m
     * swap m and n
     * decrease n
     *
     * @return
     */

    public int[] flip() {
        //Here we are modeling the matrix as a 1d array with initial size of row*cols.
        //For each flip, randomly pick an index from 0 to size-1 and flip it.
        int r = random.nextInt(size--);
        // check whether we have already put some value at this index.
        int x = map.getOrDefault(r,r);
        // swap - put total at index that we generated.
        map.put(r, map.getOrDefault(this.size, this.size));
        return new int[]{x/C, x % C};
    }

    public void reset() {
        map.clear();
        this.size = R*C;
    }

    public int toIndex(int[] arr)
    {
        return arr[0]*C + arr[1];
    }
    public static void main(String[] args) {

        RandomFlipMatrix randomFlipMatrix = new RandomFlipMatrix(4,4);
        for (int i = 0; i < 16; i++) {
            System.out.println(randomFlipMatrix.toIndex(randomFlipMatrix.flip()));
        }
    }


}
