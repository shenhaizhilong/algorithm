package com.hui.algorithm;


/**
 *https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/32/
 * 反转字符串
 * 请编写一个函数，其功能是将输入的字符串反转过来。
 *
 * 示例：
 *
 * 输入：s = "hello"
 * 返回："olleh"
 * @author: shenhaizhilong
 * @date: 2018/7/1 10:56
 */
public class ReverseString {
    public static String reverseString(String s)
    {
        if(s == null)throw new IllegalArgumentException("s is null");
        if(s.length() ==0 || s.length() ==1) return s;
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() -1; i >=0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();

    }


    public static String reverseString2(String s) {
        char c[]=s.toCharArray();
        int i =0;int j =c.length-1;
        char temp ;
        while(i<j){
            temp =c[i];
            c[i]=c[j];
            c[j]=temp;
            i++;
            j--;
        }
        return new String(c);
    }

    public static String reverseString3(String s)
    {
        return new StringBuilder(s).reverse().toString();
    }


    /**
     *https://leetcode.com/submissions/detail/170700315/
     * 557. Reverse Words in a String III
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
     *
     * Example 1:
     * Input: "Let's take LeetCode contest"
     * Output: "s'teL ekat edoCteeL tsetnoc"
     * Note: In the string, each word is separated by single space and there will not be any extra space in the string.
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        String tempStr = new StringBuilder(s).reverse().toString();
        String[] words = tempStr.split(" ");
        swapAll(words);
        return String.join(" ",words);

    }
    private static void swapAll(String[] words)
    {
      int i = 0;
      int j = words.length -1;
      while (i < j)
      {
          swap(words,i,j);
          i++;
          j--;
      }
    }
    private static void swap(String[] words, int i, int j)
    {
        String t = words[i];
        words[i] = words[j];
        words[j] = t;
    }


    public static   String reverseWords2(String s) {
        StringBuilder sb = new StringBuilder();
        String[] arr = s.split(" ");

        for (String word : arr) {
            StringBuilder temp = new StringBuilder(word);
            sb.append(temp.reverse());
            sb.append(" ");
        }
        return sb.toString().trim();

    }

    public static String reverseWords3(String s) {
        if(s==null || s.length()==0 || s.length()<2)
            return s;
        int j = 0;
        int i = 0;
        char[] chars = s.toCharArray();
        while (j < s.length()) {
            j = s.indexOf(' ', j);
            if (j != -1) {
                 reverse(chars, i, j-1);
                j ++ ;
                i = j ;
            }
            else {
                break;
            }
        }
        reverse(chars, i, s.length() - 1);
        return new String(chars);
    }
    private static void reverse(char[] chars, int l, int r ) {
        while (l < r) {
            char tmp = chars[l];
            chars[l] = chars[r];
            chars[r] = tmp;
            l ++ ;
            r -- ;
        }

    }


    /**
     *https://leetcode.com/problems/reverse-string-ii/description/
     * 541. Reverse String II
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
     * Example:
     * Input: s = "abcdefg", k = 2
     * Output: "bacdfeg"
     * Restrictions:
     * The string consists of lower English letters only.
     * Length of the given string and k will in the range [1, 10000]
     * @param s
     * @param k
     * @return
     */
    public static String reverseStr(String s, int k) {
        if(s == null || s.length() == 0 || s.length() == 1)return s;
        char[] res = s.toCharArray();
        int start = 0;
        while (start < s.length())
        {
            int end = start + 2*k -1;
            int middle = (start + k -1);
            middle = Math.min(middle, s.length() -1);
            reverse(res,start, middle); //reverse first k characters
            start = end +1;
        }

        return new String(res);


    }


    public static void main(String[] args) {
//        String s = "我是谁";
//        System.out.println(reverseString(s));
//        System.out.println(reverseString2(s));
//        System.out.println(reverseString3(s));
//
//
//        String a = "Let's take LeetCode contest";
//        System.out.println(reverseWords3(a));

        String b = "abcdefg";
        int k =2;
        System.out.println(reverseStr(b,k));

    }
}
