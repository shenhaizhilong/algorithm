package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/1 23:44
 */
public class AddBinary {
    public static String addBinary1(String a, String b) {


        int aInt = Integer.parseInt(a,2);
        int bInt = Integer.parseInt(b,2);
        int result = aInt + bInt;
        return Integer.toBinaryString(result);


    }

    public static String addBinary2(String a, String b) {

        int min = Math.min(a.length(), b.length());
        int i = 0;
        int upBit = 0;
        int positionBit = 0;
        int abit;
        int bbit;
        StringBuilder sb = new StringBuilder();
        while (i < min)
        {
            abit = a.charAt(a.length() -1 -i) - '0';
            bbit = b.charAt(b.length() -1 -i) - '0';
            positionBit = (abit ^ bbit)^upBit;
            sb.append(positionBit);
            upBit = (abit + bbit + upBit)>>>1;
            i++;

        }

        String bigger = a.length() -1  -i >=0? a: b;

        while (bigger.length() -1 -i >=0)
        {
            abit = bigger.charAt(bigger.length() -1 -i) -'0';
            positionBit = abit^upBit;
            sb.append(positionBit);
            upBit = abit & upBit;
            i++;

        }


        if(upBit ==1)
            sb.append(upBit);
        return sb.reverse().toString();





    }

    public static void main(String[] args) {

        System.out.println(addBinary2("11","1"));
        System.out.println(addBinary2("11","11"));
       System.out.println(addBinary2("1010", "1011"));
    }

}
