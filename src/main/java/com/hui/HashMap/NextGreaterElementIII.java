package com.hui.HashMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/28 18:25
 */
public class NextGreaterElementIII {

    /**
     *
     * 556. Next Greater Element III
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.
     *
     * Example 1:
     *
     * Input: 12
     * Output: 21
     *
     *
     * Example 2:
     *
     * Input: 21
     * Output: -1
     *
     *
     * https://www.geeksforgeeks.org/find-next-greater-number-set-digits/
     *
     *
     * @param n
     * @return
     */
    public static int nextGreaterElement(int n) {
        int i,j;
        char[] digits = Integer.toString(n).toCharArray();

        // I) Start from the right most digit and find the first digit that is
        //  smaller than the digit next to it.
        for (i = digits.length -1;  i > 0 ; i--) {
            if(digits[i] > digits[i -1])
                break;
        }
        // II) if no such digit can be found, the all digits are in descending order
        // means there can't be a greater number with same digits
        if(i == 0)return -1;
        // II) Find the smallest digit on right side of (i-1)'th digit that is
        // greater than number[i-1]
        int smallest = i;
        int x = digits[i-1];
        for (j = i +1; j < digits.length ; j++) {
            if(digits[j] > x && digits[j] <= digits[smallest])
                smallest = j;
        }

        swap(digits, smallest, i -1);
        reverse(digits, i, digits.length -1 );
        try {
            return Integer.valueOf(String.valueOf(digits));
        } catch (NumberFormatException e) {

        }

        return -1;

    }

    private static void reverse(char[] data, int start, int end)
    {
        while (start < end)
        {
            swap(data, start, end);
            end--;
            start++;
        }
    }

    private static void swap(char[] data, int i, int j)
    {
        char t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    public static void main(String[] args) {

        System.out.println(nextGreaterElement(12222333));
    }
}
