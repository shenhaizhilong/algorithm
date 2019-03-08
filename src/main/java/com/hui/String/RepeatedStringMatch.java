package com.hui.String;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/31 10:30
 */
public class RepeatedStringMatch {

    /**
     *
     * 686. Repeated String Match
     * DescriptionHintsSubmissionsDiscussSolution
     * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.
     *
     * For example, with A = "abcd" and B = "cdabcdab".
     *
     * Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").
     *
     * Note:
     * The length of A and B will be between 1 and 10000.
     *
     * @param A
     * @param B
     * @return
     */
    public static int repeatedStringMatch(String A, String B) {
        if(A == null || B == null)return -1;
        if(B == "")return 0;
        if(A == "")return -1;
        int[] counter = new int[128];
        for (int i = 0; i < A.length(); i++) {
            counter[A.charAt(i)]++;
        }
        for (int i = 0; i < B.length(); i++) {
            if(counter[B.charAt(i)] == 0)return -1;
        }

        int count = 1;
        StringBuilder sb = new StringBuilder(A);
        while (sb.length() <= 10000)
        {
            if(sb.toString().indexOf(B) == -1)
            {
                count++;
                sb.append(A);
            }else {
                return count;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        System.out.println(repeatedStringMatch("abcd", "cdabcdab"));

    }
}
