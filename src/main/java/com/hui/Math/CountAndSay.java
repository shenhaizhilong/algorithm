package com.hui.Math;

/**
 *
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/39/
 *
 * 数数并说
 * 报数序列是指一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 *
 * 给定一个正整数 n ，输出报数序列的第 n 项。
 *
 * 注意：整数顺序将表示为一个字符串。
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: "1"
 * 示例 2:
 *
 * 输入: 4
 * 输出: "1211"
 *
 * @author: shenhaizhilong
 * @date: 2018/7/2 20:45
 */
public class CountAndSay {
    public static String countAndSay(int n) {
        if(n <1) throw new IllegalArgumentException("n must be bigger than 0");
        if(n ==1) return "1";
        if(n == 2) return "11";
        if(n == 3) return "21";
        if(n == 4) return "1211";

        StringBuilder sb = new StringBuilder();
        String lastStr = "1211";
        int x = 4;

        while (x < n)
        {
            int count = 0;
            char c = lastStr.charAt(0);

            for (int i = 0; i < lastStr.length(); i++) {
                if(lastStr.charAt(i) == c)
                {
                    count++;
                }else  {
                    sb.append(count);
                    sb.append(c);
                    c = lastStr.charAt(i);
                    count = 0;
                    i--;
                }
                if(i == lastStr.length() -1)
                {
                    sb.append(count);
                    sb.append(c);
                }
            }

            lastStr = sb.toString();
            sb.delete(0, sb.length());

            x++;
        }

        return lastStr;

    }


    public static void main(String[] args) {
        for (int i = 5; i < 10; i++) {
            System.out.println(countAndSay(i));
        }
    }

}
