package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/1 20:12
 */
public class RotateString {

    public static boolean  rotateString(String left, String right) {

        if(left.length() != right.length())return false;
        if(left.equals(right))return true;


        int len = left.length();
        int rightIndex;
        int count;
        for (int k = 1; k < len; k++) {
            count = 1;
            for (int j = 0; j < len; j++) {
                    rightIndex = ( k + j)%len;
//                System.out.println("left: " + left.charAt(j));
//                System.out.println("right: " + right.charAt(rightIndex));
                    if(left.charAt(j) == right.charAt(rightIndex))
                    {
                        count++;
                        if(count == len)
                            return true;
                    }
            }
        }
        return false;

    }

    public static boolean  rotateString2(String left, String right)
    {
        if(left == right) return true;
        return left.length() == right.length() && (left + left).contains(right);

    }
    public static void main(String[] args) {


        List<String> list = new ArrayList<>(10);
        list.add("bqqutquvbtgouklsayfvzewpnrbwfcdmwctusunasdbpbmhnvy");

        list.add("wpnrbwfcdmwctusunasdbpbmhnvybqqutquvbtgouklsayfvze");
        list.add("gwhqmrxpuppvuiuzicencvgtuzuxkhdqcwdbiciliiapdbzocr");
        list.add("dsinzpskimfgcppsvjbhasfvqszngdhqtakrlxdmeeqakfxucy");
        list.add("abcde");
        list.add("abced");
        System.out.println(rotateString(list.get(0), list.get(1)));
        System.out.println(rotateString(list.get(2), list.get(3)));
        System.out.println(rotateString(list.get(4), list.get(5)));
    }
}
