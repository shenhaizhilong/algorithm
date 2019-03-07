package com.hui.algorithm;

/**
 * https://leetcode-cn.com/problems/roman-to-integer/description/
 * 罗马数字包含以下七种字符：I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 * @author: shenhaizhilong
 * @date: 2018/6/29 9:41
 */
public class RomanToInt {

    public static int romanToInt(String s)
    {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'M')
            {
                res +=1000;
                continue;
            }

            if(s.charAt(i) == 'D')
            {
                res +=500;
                continue;
            }

            if(s.charAt(i) == 'C')
            {
                if(i +1 < s.length() && (s.charAt(i+1) == 'D' || s.charAt(i + 1) =='M'))
                {
                    res -=100;
                    continue;
                }else {
                    res +=100;
                    continue;
                }
            }

            if(s.charAt(i) == 'L')
            {
                res +=50;
                continue;
            }

            if(s.charAt(i) == 'X')
            {
                if(i +1 < s.length() && (s.charAt(i+1) == 'L' || s.charAt(i + 1) =='C'))
                {
                    res -=10;
                    continue;
                }else {
                    res +=10;
                    continue;
                }
            }

            if(s.charAt(i) == 'V')
            {
                res +=5;
                continue;
            }

            if(s.charAt(i) == 'I')
            {
                if(i +1 < s.length() && (s.charAt(i+1) == 'V' || s.charAt(i + 1) =='X'))
                {
                    res -=1;
                }else {
                    res +=1;
                }
            }

        }
        return res;

    }

    public static String intToRoman(int num) {

        StringBuilder sb = new StringBuilder();
        while (num > 0)
        {
            while (num >= 1000)
            {
                num -= 1000;
                sb.append("M");
            }

            while (num>=900)
            {
                num -= 900;
                sb.append("CM");
            }
            while (num>=500)
            {
                num -= 500;
                sb.append("D");
            }
            while (num>=400)
            {
                num -=400;
                sb.append("CD");
            }
            while (num>=100)
            {
                num -= 100;
                sb.append("C");
            }
            while (num>=90)
            {
                num -= 90;
                sb.append("XC");
            }
            while (num>=50)
            {
                num -=50;
                sb.append("L");
            }
            while (num>=40)
            {
                num -=40;
                sb.append("XL");
            }

            while (num>=10)
            {
                num -=10;
                sb.append("X");
            }
            while (num>=9)
            {
                num -=9;
                sb.append("IX");
            }
            while (num>=5)
            {
                num -=5;
                sb.append("V");
            }
            while (num>=4)
            {
                num -=4;
                sb.append("IV");
            }
            while (num>0)
            {
                sb.append("I");
                num -=1;
            }

        }

        return sb.toString();
    }

    /**
     *
     *
     *
     * 2. Integer to Roman
     * DescriptionHintsSubmissionsDiscussSolution
     * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
     *
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
     *
     * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
     *
     * I can be placed before V (5) and X (10) to make 4 and 9.
     * X can be placed before L (50) and C (100) to make 40 and 90.
     * C can be placed before D (500) and M (1000) to make 400 and 900.
     * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
     *
     * Example 1:
     *
     * Input: 3
     * Output: "III"
     * Example 2:
     *
     * Input: 4
     * Output: "IV"
     * Example 3:
     *
     * Input: 9
     * Output: "IX"
     * Example 4:
     *
     * Input: 58
     * Output: "LVIII"
     * Explanation: C = 100, L = 50, XXX = 30 and III = 3.
     * Example 5:
     *
     * Input: 1994
     * Output: "MCMXCIV"
     * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
     * @param num
     * @return
     */
    public static String intToRoman2(int num) {
        int[] vals = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (num >0)
        {
            while (num >= vals[index])
            {
                sb.append(romans[index]);
                num -= vals[index];
            }
            index++;
        }

        return sb.toString();

    }

    public String intToRoman3(int num) {
        if (num < 1 || num >=4000){
            return "";
        }

        String[] M = {"", "M", "MM", "MMM"};
        String[] C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        StringBuilder result = new StringBuilder();
        result.append(M[num/1000]);
        result.append(C[(num%1000)/100]);
        result.append(X[(num%100)/10]);
        result.append(I[num%10]);

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));

        System.out.println(intToRoman2(1));
        System.out.println(intToRoman2(3));
        System.out.println(intToRoman2(4));
        System.out.println(intToRoman2(9));
        System.out.println(intToRoman2(58));
        System.out.println(intToRoman2(1994));

    }
}
