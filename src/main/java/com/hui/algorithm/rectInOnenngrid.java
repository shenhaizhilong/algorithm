package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/18 15:54
 *
 *百度面试：n*n的网格有多少长方形
 *
 */
public class rectInOnenngrid {

    public int count(int n)
    {
        if(n < 2)return 0;
        int ans = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 1; y <= n; y++) {
                ans += (n -x)*y - Math.min(y, n -x);
            }
        }

        return ans;
    }


    public int count(int n, int m)
    {
        if(n < 2)return 0;
        int ans = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 1; y <= m; y++) {
                ans += (n -x)*y - Math.min(y, n -x);
            }
        }

        return ans;
    }


    //
   //x=min(m,n)-1
   //长方形里面数正方形的个数计算公式:m*n+(m-1)*(n-1)+.....+(m-x)*(n-x)
  // m*n表示长度为1的正方形的个数,(m-1)*(n-1)表示长度为2的正方形的个数。。。。。。
  //长方形里面数长方形的个数计算公式(包含正方形):(1+2+3+...+m)*(1+2+3+...+n)=n*m(n+1)*(m+1)/4

    public int[] countRectAndSquare(int n, int m)
    {

        int[] ans = {0,0};// {count of Rect, count of Square};
        int x = Math.min(n,m) -1;
        int sumS = 0;
        int SumR = n*m*(n+1)*(m +1)/4; // sum of all rects, including squares.
        for (int i = 0; i <= x; i++) {
            sumS += (m - i)*(n - i);
        }
        ans[0] = SumR - sumS;
        ans[1] = sumS;
        return ans;

    }

    public static void main(String[] args) {
        rectInOnenngrid rectInOnenngrid = new rectInOnenngrid();
        for (int i = 1; i < 6; i++) {
            System.out.println(rectInOnenngrid.count(i));
        }

        System.out.println(rectInOnenngrid.count(5,4));
        System.out.println(Arrays.toString(rectInOnenngrid.countRectAndSquare(2,3)));
    }
}
