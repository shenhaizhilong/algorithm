package com.hui.String;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *最后一个单词的长度
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 *
 * 如果不存在最后一个单词，请返回 0 。
 *
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 *
 * 示例:
 *
 * 输入: "Hello World"
 * 输出: 5
 * @author: shenhaizhilong
 * @date: 2018/7/1 21:05
 */
public class LengthOfLastWord {
    public static int lengthOfLastWord(String s) {

        Pattern pattern = Pattern.compile("(\\w+)");
        Matcher matcher = pattern.matcher(s);
        int len = 0;
        String word;
       while (matcher.find())
       {

           word = matcher.group(1);
           System.out.println(word);
           len = word.length();
       }
        return len;


    }


    public static int lengthOfLastWord2(String s) {

        String[] strs = s.trim().split(" ");
        if(strs.length == 0)return 0;
        int len = strs.length;
        return strs[len -1].length();


    }

    public static void main(String[] args) {
        String s = "Following example illustrates how to find a digit string from the given alphanumeric ";
        System.out.println(lengthOfLastWord(s));

        System.out.println(lengthOfLastWord2(s));

    }
}
