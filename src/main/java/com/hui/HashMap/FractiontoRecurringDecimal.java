package com.hui.HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/3 11:33
 */
public class FractiontoRecurringDecimal {


    /**
     *
     * 166. Fraction to Recurring Decimal
     * DescriptionHintsSubmissionsDiscussSolution
     * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
     *
     * If the fractional part is repeating, enclose the repeating part in parentheses.
     *
     * Example 1:
     *
     * Input: numerator = 1, denominator = 2
     * Output: "0.5"
     * Example 2:
     *
     * Input: numerator = 2, denominator = 1
     * Output: "2"
     * Example 3:
     *
     * Input: numerator = 2, denominator = 3
     * Output: "0.(6)"
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0)return "0";
        StringBuilder sb = new StringBuilder();
        int sign = Integer.signum(numerator)*Integer.signum(denominator);
        if(sign < 0)
        {
            sb.append('-');
        }


        // change it to long type, because if int numerator == Integer.Min and demoinator is -1, overflow occurred.
        long n = Math.abs((long)numerator);
        long d = Math.abs((long)denominator);
        long r = n % d;
        sb.append(n/d); // integral part.
        if(r == 0)return sb.toString();  // no fractional part.
        sb.append('.');  //  has fractional part, append the dot.
        Map<Long, Integer> map = new HashMap<>();
        while (r != 0)
        {

            // if we have seen r,  we should stop now.
            if(map.getOrDefault(r, -1).intValue() > 0)
            {
                sb.insert(map.get(r).intValue(), '(');
                sb.append(')');
                break;
            }


            // recording r's position.
            map.put(r, sb.length());
            r *= 10;
            sb.append(r/d);
            r %= d;
        }
       return   sb.toString();
    }

    public static void main(String[] args) {

        FractiontoRecurringDecimal fractiontoRecurringDecimal = new FractiontoRecurringDecimal();
        System.out.println(fractiontoRecurringDecimal.fractionToDecimal(1,7));
        System.out.println(fractiontoRecurringDecimal.fractionToDecimal(-1,Integer.MIN_VALUE));
        System.out.println(fractiontoRecurringDecimal.fractionToDecimal(1,17));
    }
}
