package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/4 11:10
 *
 *
 */
public class LargestTimeforGivenDigits {

    public String largestTimeFromDigits(int[] A) {
        // 0 + 1 + 2 + 3 = 6
        String ans  = "";
        int max = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(i == j)continue;
                for (int k = 0; k < 4; k++) {
                    if(i == k || j == k)continue;
                    int L = 6 - i - j - k;
                    int hour = 10*A[i]+A[j];
                    int mins = 10*A[k] + A[L];
                    if(hour < 24 && mins < 60)
                    {
                        max = Math.max(hour*60 + mins, max);
                    }
                }

            }
        }
        if(max == -1)return ans;
        return String.format("%02d:%02d",max/60 ,(max % 60));
    }

    public static void main(String[] args) {
        LargestTimeforGivenDigits largestTimeforGivenDigits = new LargestTimeforGivenDigits();
        System.out.println(largestTimeforGivenDigits.largestTimeFromDigits(new int[]{5,5,5,5}));
        System.out.println(largestTimeforGivenDigits.largestTimeFromDigits(new int[]{1,2,3,4}));
        System.out.println(largestTimeforGivenDigits.largestTimeFromDigits(new int[]{0,0,0,0}));
    }
}
