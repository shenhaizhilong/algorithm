package com.hui.algorithm;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/4 15:33
 */
public class ImageOverlap {


    /**
     *835. Image Overlap
     * DescriptionHintsSubmissionsDiscussSolution
     * Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)
     *
     * We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.
     *
     * (Note also that a translation does not include any kind of rotation.)
     *
     * What is the largest possible overlap?
     *
     * Example 1:
     *
     * Input: A = [[1,1,0],
     *             [0,1,0],
     *             [0,1,0]]
     *        B = [[0,0,0],
     *             [0,1,1],
     *             [0,0,1]]
     * Output: 3
     * Explanation: We slide A to right by 1 unit and down by 1 unit.
     * Notes:
     *
     * 1 <= A.length = A[0].length = B.length = B[0].length <= 30
     * 0 <= A[i][j], B[i][j] <= 1
     *
     * @param A
     * @param B
     * @return
     */
    public int largestOverlap(int[][] A, int[][] B) {
        int N = A.length;
        List<int[]> listA = new ArrayList<>();
        List<int[]> listB = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(A[i][j] == 1){
                    listA.add(new int[]{i,j});  //append when A[i][j] = 1
                }
                if(B[i][j] == 1){
                    listB.add(new int[]{i,j}); //append when B[i][j] = 1
                }
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int[] a : listA) {
            for (int[] b : listB)
            {
                int[] offset = new int[]{a[0] - b[0], a[1] - b[1]}; // offset
                int hash =hashCode(offset);
                // overlap regions have the same offset.
               map.put(hash, map.getOrDefault(hash, 0) +1);
               res = Math.max(res, map.get(hash));
            }
        }

        return res;

    }


    // i in [0,30], j in [0,30];
    //p1 is a prime larger than 30
    //p2 is a prime
    // assume there is hash conflict, so i*p1 + j*p2 = i'*p1 + j'*p2, and i !=i' , j !=j'.
    // (i-i')*p1 = (j' - j)*p2
    // so (i-i') == p2, (j' - j) == p1
    // j' = p1 + j, due to p1 > 30 , j  in [0,30], so j' is larger than 30, but it's impossible.
    //
    private int hashCode(int[] arr)
    {
        if(arr == null || arr.length < 2)return 0;
        // return arr[0]*17 + arr[1]*31;
        return arr[0]*1231 + arr[1]*1237;

    }

    public static void main(String[] args) {

        int[][] A = {{1,1,0},{0,1,0},{0,1,0}};
        int[][] B = {{0,0,0},{0,1,1},{0,0,1}};
        ImageOverlap imageOverlap = new ImageOverlap();
        System.out.println(imageOverlap.largestOverlap(A, B));



        int[] a = {0,0};
        System.out.println(Arrays.hashCode(a));
        System.out.println(a.hashCode());
        int[] b = {0,0};
        System.out.println(Arrays.hashCode(b));
        System.out.println(b.hashCode());

       int[][] AA = {{0,1,0,0,1,1,1,0,1,0,0,0,1,1,1,1,0,1,0,1,1,1,0,1,1,0,1,1,1,0},{1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,0,1,1,0,1,1,1,1,1,1,1,0,1,1},{1,1,1,1,0,0,1,1,0,1,1,1,0,1,0,1,1,1,0,0,0,0,1,1,0,1,1,1,1,0},{1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,1,0,1,1,1,1,0,1,1,1,1},{1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,0},{0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,0,1,1,0,1,0,1,1,0,1,1},{1,1,1,0,0,1,1,1,1,1,1,1,0,0,0,0,1,1,0,0,1,1,1,1,1,1,1,1,0,1},{1,0,1,1,0,1,0,1,1,0,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,1,1,1},{1,1,0,1,0,0,0,1,1,0,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0},{0,0,1,1,0,0,1,0,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1},{0,1,1,0,1,0,1,1,0,1,1,1,1,1,1,0,0,1,1,1,1,0,0,0,1,1,1,1,1,1},{0,1,0,0,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0,0,1,1,1},{1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0},{1,1,0,0,1,1,1,1,1,0,1,1,0,1,1,0,0,1,0,0,1,1,0,1,0,1,1,1,1,1},{1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,1,1},{1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,0,1,0,0,1,1,1,0,0,0,1,1},{1,1,1,1,1,1,0,0,0,1,0,1,1,0,1,1,0,1,1,1,1,0,1,1,1,1,1,1,1,0},{1,1,0,1,1,1,1,0,0,1,0,1,1,1,0,1,1,0,0,1,1,1,1,1,1,0,1,1,1,0},{1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,1,1,0,1,1,1,1,0,1,1,1,0,1,1},{1,1,1,1,1,1,0,0,1,1,0,1,1,1,1,1,0,0,0,1,0,1,1,0,1,1,1,1,1,0},{0,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,0,1,1,1,1,0,1},{1,1,1,1,0,0,0,0,1,1,0,0,0,1,0,0,1,1,1,0,1,1,1,0,1,1,0,0,1,1},{1,1,1,0,1,1,0,1,1,1,1,0,1,1,1,1,0,1,1,1,0,0,1,1,1,0,1,1,1,1},{1,0,0,1,1,1,1,1,1,1,1,0,1,0,1,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1},{1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,0,0,1,1,1,1,1,0,1},{0,1,0,1,0,1,0,1,1,1,1,1,1,0,1,1,0,0,1,0,1,1,1,0,0,1,1,1,1,1},{1,1,1,0,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1},{1,0,0,1,0,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1},{1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1},{1,1,1,0,0,0,1,1,0,1,0,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1}};
        int[][] BB = {{1,1,1,1,1,0,1,0,1,1,0,1,1,1,1,1,1,1,0,0,1,0,1,1,1,1,0,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,0,1,0,1,0,0,1,0,1,1,1,1,1,1},{1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},{1,1,1,1,1,1,1,1,1,0,0,1,1,0,1,0,0,0,1,1,0,0,1,1,1,0,1,1,1,0},{1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,0,0,1,1},{1,1,0,0,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1},{0,0,1,1,0,1,1,0,1,1,1,0,1,1,0,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1},{0,0,0,0,1,1,0,1,0,1,0,1,0,1,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1},{1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,0,1,0,1,1,0,1,1,1,1,1},{1,1,0,1,0,0,1,1,1,1,1,0,1,1,0,1,1,1,0,1,1,1,1,1,1,1,0,0,0,1},{1,0,1,0,0,1,1,0,0,1,1,1,1,1,1,0,0,1,0,1,1,0,1,0,1,0,0,0,1,0},{1,1,0,0,1,1,1,1,1,1,1,0,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,0,1,1},{1,0,1,1,1,1,1,1,1,0,1,1,1,0,0,0,1,1,1,1,1,1,1,1,0,1,0,1,1,1},{1,0,0,1,1,1,0,1,1,0,0,1,1,1,0,1,1,1,1,1,0,1,1,0,1,0,1,1,1,1},{1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,0,1,1,0,1},{0,1,0,0,0,1,1,1,1,1,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,1,0,1},{1,1,0,0,0,1,0,1,1,0,1,0,1,0,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,0,0,1,1,0,0,0,1,0,0,1,1,1,1,1,1,1,1,0,1,0,1,1},{1,0,0,1,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1},{1,0,0,1,0,0,1,0,1,0,1,1,1,0,0,1,1,0,1,0,0,1,0,1,1,1,0,1,0,1},{1,1,1,1,1,0,0,1,0,0,1,0,1,1,1,1,1,0,0,1,0,1,1,1,1,0,1,1,1,1},{0,0,0,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,0,1,0,1,1,1,1,1,1,0,0},{1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,1,0,1,1,1,1,1,1,1,0,0,1,1},{1,0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,0,1,1,0,1,1,1},{0,1,1,1,0,1,0,0,0,1,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,1,0,1},{1,0,1,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,0,1,0,1,0,1,1,0},{1,1,1,1,1,0,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1},{1,1,1,0,1,1,1,1,1,1,1,1,0,1,0,1,1,0,1,1,1,1,0,1,1,1,0,1,0,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1},{0,1,0,0,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,0}};
        System.out.println(imageOverlap.largestOverlap(AA, BB));


    }
}
