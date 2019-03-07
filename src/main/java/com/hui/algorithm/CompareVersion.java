package com.hui.algorithm;

/**
 * https://leetcode-cn.com/problems/compare-version-numbers/description/
 *
 * 比较两个版本号 version1 和 version2。
 * 如果 version1 > version2 返回 1，如果 version1 < version2 返回 -1， 除此之外返回 0。
 *
 * 你可以假设版本字符串非空，并且只包含数字和 . 字符。
 *
 *  . 字符不代表小数点，而是用于分隔数字序列。
 *
 * 例如，2.5 不是“两个半”，也不是“差一半到三”，而是第二版中的第五个小版本。
 *
 * 示例 1:
 *
 * 输入: version1 = "0.1", version2 = "1.1"
 * 输出: -1
 * 示例 2:
 *
 * 输入: version1 = "1.0.1", version2 = "1"
 * 输出: 1
 * 示例 3:
 *
 * 输入: version1 = "7.5.2.4", version2 = "7.5.3"
 * 输出: -1
 * @author: shenhaizhilong
 * @date: 2018/6/29 14:20
 */
public class CompareVersion {
    public static int compareVersion(String version1, String version2) {

        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int min = Math.min(v1.length, v2.length);
        for (int i = 0; i < min; i++) {
            if(Integer.valueOf(v1[i]) > Integer.valueOf(v2[i]))
                return 1;
            if(Integer.valueOf(v1[i]) < Integer.valueOf(v2[i]))
                return -1;
        }


        int res = v1.length - v2.length;
        if(res  > 0)
        {
            for (int i = min ; i <v1.length ; i++) {
                if(!Integer.valueOf(v1[i]).equals(0))return 1;
            }
        }else if(res < 0)
        {
            for (int i = min ; i <v2.length ; i++) {
                if(!Integer.valueOf(v2[i]).equals(0))return -1;
            }
        }

        return 0 ;

    }

    public static void main(String[] args) {
        System.out.println(compareVersion("000000001.0", "1"));
        System.out.println(compareVersion("1.2", "1.10"));
        System.out.println(compareVersion("0.1", "1.1"));
        System.out.println(compareVersion("1.0.1", "1"));
        System.out.println(compareVersion("7.5.2.4", "7.5.3"));
    }
}
