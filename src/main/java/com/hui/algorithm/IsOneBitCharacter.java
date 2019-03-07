package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/2 18:21
 */
public class IsOneBitCharacter {

    /**
     *
     * 717. 1-bit and 2-bit Characters
     * DescriptionHintsSubmissionsDiscussSolution
     * We have two special characters. The first character can be represented by one bit 0. The second character can be represented by two bits (10 or 11).
     *
     * Now given a string represented by several bits. Return whether the last character must be a one-bit character or not. The given string will always end with a zero.
     *
     * Example 1:
     * Input:
     * bits = [1, 0, 0]
     * Output: True
     * Explanation:
     * The only way to decode it is two-bit character and one-bit character. So the last character is one-bit character.
     * Example 2:
     * Input:
     * bits = [1, 1, 1, 0]
     * Output: False
     * Explanation:
     * The only way to decode it is two-bit character and two-bit character. So the last character is NOT one-bit character.
     * Note:
     *
     * 1 <= len(bits) <= 1000.
     * bits[i] is always 0 or 1.
     * @param bits
     * @return
     */
    public static boolean isOneBitCharacter(int[] bits) {
        if(bits[bits.length -1] != 0)return false;
        int slow = 0;
        while (slow < bits.length)
        {
            if(bits[slow] == 0)
            {
                if(slow == bits.length -1)
                    return true;
                slow++;
            }else {
                slow +=2;
            }
        }
        return false;
    }

    //last one always is 0
    public static boolean isOneBitCharacter2(int[] bits) {
        int i = bits.length -2;
        while (i>=0 && bits[i] == 1)i--;
        return (bits.length - i)%2 == 0;
    }

    public static void main(String[] args) {


        System.out.println(isOneBitCharacter2(new int[]{1,0,0}));
        System.out.println(isOneBitCharacter2(new int[]{1,1,1,0}));
        System.out.println(isOneBitCharacter2(new int[]{0,0,0,0}));
        System.out.println(isOneBitCharacter2(new int[]{0,0,1,0}));
        System.out.println(isOneBitCharacter2(new int[]{0,0,1,0}));
        System.out.println(isOneBitCharacter2(new int[]{0,0,1,1,0}));
    }
}
