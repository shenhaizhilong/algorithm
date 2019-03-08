package com.hui.Geometry;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/17 18:16
 *
 *976. Largest Perimeter Triangle
 * Easy
 *
 * 46
 *
 * 4
 *
 * Favorite
 *
 * Share
 * Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.
 *
 * If it is impossible to form any triangle of non-zero area, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: [2,1,2]
 * Output: 5
 * Example 2:
 *
 * Input: [1,2,1]
 * Output: 0
 * Example 3:
 *
 * Input: [3,2,3,4]
 * Output: 10
 * Example 4:
 *
 * Input: [3,6,2,3]
 * Output: 8
 *
 *
 * Note:
 *
 * 3 <= A.length <= 10000
 * 1 <= A[i] <= 10^6
 *
 */
public class LargestPerimeterTriangle {

    public int largestPerimeter(int[] A) {
        if(A == null || A.length < 3)return 0;
        int ans = 0;
        double maxArea = 0;
        for (int i = 0; i < A.length -2; i++) {
            for (int j = i +1; j < A.length - 1; j++) {
                for (int k = j +1; k < A.length; k++) {
                    if(isTriangle(A[i],A[j],A[k]))
                    {
                        double area = getArea(A[i],A[j],A[k]);
                        if(area > maxArea)
                        {
                            ans = A[i] + A[j] + A[k];
                            maxArea = area;
                        }
                    }
                }
            }
        }
        return ans;
    }

    private boolean isTriangle(int a, int b, int c)
    {
        if( a + b <= c || a + c <= b || b +c <= a)return false;
        return true;
    }

    private double getArea(int a, int b, int c)
    {
        double p = (a + b + c)/2.0D;
        return p*(p -a)*(p -b)*(p -c);
    }


    public int largestPerimeter2(int[] A) {
        if(A == null || A.length < 3)return 0;
        Arrays.sort(A);
        // A[i] >= A[i -1] >= A[i -2];  A[i] + A[i -1] > A[i -2]; A[i] + A[i -2] > A[i -1]; so just need to test
        // A[i -1] + A[i -2] > A[i], if is triangle then return it.
        for(int i = A.length -1; i >= 2; i--)
        {
            if(A[i -1] + A[i -2] > A[i])return A[i] + A[i -1] + A[i -2];
        }
        return 0;
    }

    public static void main(String[] args) {
        LargestPerimeterTriangle triangle = new LargestPerimeterTriangle();
        System.out.println(triangle.largestPerimeter(new int[]{2,1,2}));
        System.out.println(triangle.largestPerimeter(new int[]{1,2,1}));
        System.out.println(triangle.largestPerimeter(new int[]{3,2,3,4}));
        System.out.println(triangle.largestPerimeter(new int[]{3,6,2,3}));

        System.out.println("****************");
        System.out.println(triangle.largestPerimeter2(new int[]{2,1,2}));
        System.out.println(triangle.largestPerimeter2(new int[]{1,2,1}));
        System.out.println(triangle.largestPerimeter2(new int[]{3,2,3,4}));
        System.out.println(triangle.largestPerimeter2(new int[]{3,6,2,3}));
    }
}
