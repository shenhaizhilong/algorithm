package com.hui.String;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/20 12:06
 *
 * for a string, right shift k time:
 * for an example:  abcd1234 ->
 * rifht shift k == 4 ;
 * abcd1234 ===> reverse(abcd) + reverse(1234) => dcba4321 ===> reverse(dcba4321) ==> 1234abcd
 *
 *               reverse(vals,0, N -k -1) , reverse(vals,N -k, N-1),reverse(all)
 * right shift k == 5 ;
 * abcd1234 ===> reverse(abc) + reverse(d1234) => cba4321d ====> reverse(cba4321d) ===> d1234abc
 *
 *
 * left shift k == 5
 *               reverse(vals,0, k -1) , reverse(vals,k, N-1),reverse(all)
 *
 * abcd1234 ===> reverse(abcd1) + reverse(234) => 1dcba432 ====> reverse(1dcba432) ===> 234abcd1
 * 234abcd1
 */
public class RightShift {
    public String rightShift(String str, int k)
    {
        int N = str.length();
        k %= N;
        char[] vals = str.toCharArray();
        reverse(vals, 0, N -k -1);
        reverse(vals, N - k, N -1);
        reverse(vals,0, N -1);
        return String.valueOf(vals);
    }

    // left shift k == 5 ; d1234abc ===> reverse(abc) + reverse(d1234) => cba4321d ====> reverse(cba4321d) ===> d1234abc
    public String leftShift(String str, int k)
    {
        int N = str.length();
        k %= N;
        char[] vals = str.toCharArray();
        reverse(vals, 0, k -1);
        reverse(vals, k, N -1);
        reverse(vals,0, N -1);
        return String.valueOf(vals);
    }

    private void reverse(char[] vals, int start, int end)
    {
        while (start < end)
        {
            char temp = vals[start];
            vals[start] = vals[end];
            vals[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        RightShift rightShift = new RightShift();
        System.out.println(rightShift.rightShift("abcdefg",3));
        System.out.println(rightShift.leftShift("abcdefg",3));
    }
}
