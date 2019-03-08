package com.hui.BitOperation;

import java.util.BitSet;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/11 11:37
 */
public class BitSetDemo {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet((1<<30));
        for (int i = 0; i < 32; i+=2) {
            bitSet.set(i,true);
        }
        for (int i = 0; i < 32; i++) {
            System.out.println(bitSet.get(i));
        }
        System.out.println(bitSet.toString());

        System.out.println(bitSet.length());
    }
}
