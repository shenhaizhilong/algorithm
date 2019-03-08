package com.hui.String;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/31 22:05
 */
public class LicenseKeyFormatting {


    public static String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        char[] chars = S.toCharArray();
        int count = 0;
        int start = 0;
        while (start <chars.length && chars[start] == '-')start++;

        for (int i = chars.length -1; i >=start ; i--) {
            char c = chars[i];
            if(c != '-')
            {
                count++;
                sb.append(Character.toUpperCase(c));
                if(count == K)
                {
                    if(i != start)
                        sb.append('-');
                    count = 0;
                }

            }

        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(licenseKeyFormatting("5F3Z-2e-9-w", 4));
        System.out.println(licenseKeyFormatting("--a-a-a-a--" ,2));
    }
}
