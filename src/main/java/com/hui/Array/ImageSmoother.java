package com.hui.Array;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/1 15:56
 */
public class ImageSmoother {

    /**
     *
     * 661. Image Smoother
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.
     *
     * Example 1:
     * Input:
     * [[1,1,1],
     *  [1,0,1],
     *  [1,1,1]]
     * Output:
     * [[0, 0, 0],
     *  [0, 0, 0],
     *  [0, 0, 0]]
     * Explanation:
     * For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
     * For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
     * For the point (1,1): floor(8/9) = floor(0.88888889) = 0
     * Note:
     * The value in the given matrix is in the range of [0, 255].
     * The length and width of the given matrix are in the range of [1, 150].
     *
     * @param M
     * @return
     */
    public int[][] imageSmoother(int[][] M) {
        int[][] res = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                res[i][j] = average(M,i,j);
            }
        }

        return res;
    }

    private int average(int[][] M, int i, int j){
        int rowStart = (i -1) >=0 ? (i -1) : 0;
        int rowEnd = (i + 1) <=M.length -1 ?(i +1): M.length -1;
        int colStart = (j - 1) >=0 ? (j -1): 0;
        int colEnd = (j + 1) <=M[0].length -1 ?(j + 1):M[0].length -1;
        int sum = 0;
        int count = 0;
        for (int k = rowStart; k <= rowEnd; k++) {
            for (int l = colStart; l <=colEnd ; l++) {
                sum += M[k][l];
                count++;
            }
        }

        return sum/count;
    }

    public static void main(String[] args) {


        int[][] M = {{1,1,1},
                    {1,8,1},
                    {1,1,1}};
        ImageSmoother imageSmoother = new ImageSmoother();
        int[][] res = imageSmoother.imageSmoother(M);
        for (int i = 0; i < res.length; i++) {
            System.out.println(Arrays.toString(res[i]));
        }
    }
}
