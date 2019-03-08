package com.hui;

/**
 * @author: shenhaizhilong
 * @date: 2018/6/25 7:17
 */


public class Example {

   private String str = new String("good");

   private char[] ch = { 'a', 'b', 'c' };

    public static void main(String args[]) {

        Example ex = new Example();
        ex.change(ex.str, ex.ch);

        System.out.print(ex.str + " and ");

        System.out.print(ex.ch);
        System.out.println(getValue(2));

        String b = new String("dd");
        chageStr(b);
        System.out.println(b);



    }

    public void change(String str, char ch[]) {

        str = "test ok";

        ch[0] = 'g';

    }

    public static void chageStr(String str)
    {
        str = str.substring(2);
    }

    public static int getValue(int i) {
        int result = 0;
        switch (i) {
            case 1:
                result = result + i;
            case 2:
                result = result + i * 2;
            case 3:
                result = result + i * 3;
        }
        return result;
    }
}