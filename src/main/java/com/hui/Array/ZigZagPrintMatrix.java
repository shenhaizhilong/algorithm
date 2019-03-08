package com.hui.Array;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/21 10:58
 *
 *
 * 之字形打印矩阵
 *
 */
public class ZigZagPrintMatrix {

    public int[] zigZigOrder(int[][] matrix)
    {
        boolean up = true;
        int R = matrix.length;
        int C = matrix[0].length;
        int N = R*C;
        int[] ans = new int[N];
        int idx = 0;
        int r = 0;
        int c = 0;
        while (idx < N)
        {
            ans[idx++]= matrix[r][c];
            if(up)
            {
                if(r == 0)
                {
                    if(c != C -1)
                    {
                        c++;
                    }else {
                        r++;
                    }

                    up = !up;
                }else if(c == C -1)
                {
                    up = !up;
                    r++;
                }else {
                    r--;
                    c++;
                }
            }else {
                if(c == 0)
                {
                    if(r != R - 1)
                    {
                        r++;
                    }else {
                        c++;
                    }
                    up = !up;
                }else if(r == R -1)
                {
                    c++;
                    up = !up;
                }else
                {
                    r++;
                    c--;
                }
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        int R = 8;
        int C = 8;
        int[][] matrix = new int[R][C];
        int val = 10;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                matrix[i][j] = val++;
            }
        }
        Matrix.print(matrix);
        ZigZagPrintMatrix zigZagPrintMatrix = new ZigZagPrintMatrix();
        Matrix.print(zigZagPrintMatrix.zigZigOrder(matrix));
    }
}
