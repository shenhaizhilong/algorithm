package com.hui.String;

/**
 *
 * https://leetcode-cn.com/problems/multiply-strings/description/
 * 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @author: shenhaizhilong
 * @date: 2018/7/2 19:27
 */
public class StringMultiply {
    public static String multiply(String num1, String num2) {
        if(num1 =="0" || num2 =="0") return "0";
        int[] product = new int[num1.length() + num2.length()];
        for (int i = num1.length() -1; i >=0; i--) {
            for (int j = num2.length() -1; j>=0; j--) {
                int n1c = num1.charAt(i) - '0';
                int n2c = num2.charAt(j) - '0';
                int k =  num1.length() -1 -i + num2.length() -1 -j;
                product[k] += (n1c * n2c);
               // System.out.println("k = " + k + " , product =" + product[k]);
                while (product[k] > 9) {
                    product[k + 1] += product[k] / 10;  // 进位
                    product[k] = product[k] % 10;      // 个位
                    k++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        int lastIndex = product.length - 1;
        for (; lastIndex > 0; lastIndex--)
        {
            if(product[lastIndex] != 0){
                break;
            }

        }
        for (int i = lastIndex; i >=0 ; i--) {
            sb.append(product[i]);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("123","3"));
        System.out.println(multiply("123","123"));
        System.out.println(multiply("123","1"));
        System.out.println(multiply("123","0"));
        System.out.println(multiply("123","456"));

    }
}
