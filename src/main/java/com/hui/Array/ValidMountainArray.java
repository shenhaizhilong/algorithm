package com.hui.Array;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/20 12:59
 */
public class ValidMountainArray {
    public boolean validMountainArray(int[] A) {
        if(A == null || A.length < 3)return false;
        int N = A.length;
        int i = 0;

        //up, i is the peak
        while (i +1 < N && A[i] < A[i +1])i++;
        if(i == 0 || i == N -1)return false;
        i++;
        // down
        while (i  < N && A[i] < A[i- 1])i++;
        return i == N;

    }

    public boolean validMountainArray2(int[] A) {
        if(A == null || A.length < 3)return false;
        int N = A.length;
        int j = N -1;
        int i = 0;

        //up, i is the peak
        // Two people climb from left and from right separately.
        //If they are climbing the same mountain,
        //they will meet at the same point.
        while (i +1 < N && A[i] < A[i +1])i++;
        while (j > 0 && A[j -1] > A[j])j--;
        return i > 0 && i == j && j < N -1;

    }


    public static void main(String[] args) {
        ValidMountainArray validMountainArray = new ValidMountainArray();
        System.out.println(validMountainArray.validMountainArray(new int[]{0,3,2,1}));
        System.out.println(validMountainArray.validMountainArray2(new int[]{0,3,2,1}));
    }

}
