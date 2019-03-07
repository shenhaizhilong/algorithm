package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/22 12:15
 *
 *927. Three Equal Parts
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array A of 0s and 1s, divide the array into 3 non-empty parts such that all of these parts represent the same binary value.
 *
 * If it is possible, return any [i, j] with i+1 < j, such that:
 *
 * A[0], A[1], ..., A[i] is the first part;
 * A[i+1], A[i+2], ..., A[j-1] is the second part, and
 * A[j], A[j+1], ..., A[A.length - 1] is the third part.
 * All three parts have equal binary value.
 * If it is not possible, return [-1, -1].
 *
 * Note that the entire part is used when considering what binary value it represents.  For example, [1,1,0] represents 6 in decimal, not 3.  Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,0,1,0,1]
 * Output: [0,3]
 * Example 2:
 *
 * Input: [1,1,0,1,1]
 * Output: [-1,-1]
 *
 *
 * Note:
 *
 * 3 <= A.length <= 30000
 * A[i] == 0 or A[i] == 1
 *
 *
 *
 *
 */
public class ThreeEqualParts {

    public int[] threeEqualParts(int[] A) {

        int countOne = 0;
        for(int a:A)
        {
            if(a == 1)countOne++;
        }
        // no one, all bits are zero
        if(countOne == 0)return new int[]{0,A.length -1};
        if(countOne % 3 != 0)return new int[]{-1,-1};

        int end = A.length -1;
        int first = 0, second = 0, third = 0;
        int k = countOne /3;  // the number of one in each part

        //  k = countOne /3
        //          k      k+1         2k+1
        //          |      |           |
        //  0,0,0,111,   0,111,    0,0,111
        //        |        |           |
        //        fir      sec         third

        while (first < end && A[first] == 0 )first++;
        int countS = 0;
        second = first;
        while (countS != k +1 && second < end)
        {
            if(A[second] == 1)
                countS++;
            second++;
        }
        second--;

        third = second + 1;
        while (countS != 2*k +1 && third <= end)
        {
            if(A[third] == 1)
                countS++;
            third++;
        }
        third--;

        while (third <= end && A[first] == A[second] && A[second] == A[third])
        {
            first++;
            second++;
            third++;
        }
        if(third != A.length)return new int[]{-1,-1};
        return new int[]{first -1, second};

    }

    public static void main(String[] args) {
        ThreeEqualParts threeEqualParts = new ThreeEqualParts();
        Matrix.print(threeEqualParts.threeEqualParts(new int[]{1,0,1,0,1}));
    }
}
