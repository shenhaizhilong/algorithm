package com.hui.Recursive;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/2 15:22
 *
 *
 * N个人过河，船每次最多只能坐两个人，船载每个人过河的所需时间不同，问最快的过河时间。
 * https://blog.csdn.net/wuzhekai1985/article/details/6846934
 *
 */
public class CrossingRiver {

    public int minCost1(int[] time, int n)
    {
        if(n == 1)return time[0];
        else if(n == 2)return time[1];
        else if(n == 3)return time[0] + time[1] + time[2];
        else {
            // 1 fast and second fast;
            // 2.fast back
            // 3 most slow and second slow
            // 4 second fast back
            int t1 = time[1] + time[0] + time[n-1] + time[1];
            // 1 fast and most slow;
            // 2.fast back
            // 3 fast and second slow
            // 4  fast back
            int t2 = time[n-1] + time[0] + time[n-2] + time[0];
            int t = t1 > t2 ? t2: t1;
//            if(t1 < t2)
//            {
//                Matrix.print(new int[]{0,1,0,n-1,n-2,1});
//            }else {
//                Matrix.print(new int[]{0,n-1,0,0,n-2,0});
//            }
            return minCost1(time, n -2) + t;

        }
    }

    public int minCost2(int[] time, int n)
    {
        // if time is not sorted
      //  Arrays.sort(time);
        if(n == 1)return time[0];
        else if(n == 2)return time[1];
        else if(n == 3)return time[0] + time[1] + time[2];
        int sum = 0;
        while (n > 0)
        {
            if(n == 2)return sum += time[1];
            else if(n == 3)return sum += time[0] + time[1] + time[2];
            else {
                int t1 = time[1] + time[0] + time[n-1] + time[1];
                int t2 = time[n-1] + time[0] + time[n-2] + time[0];
                int t = t1 > t2 ? t2: t1;
                sum += t;
                n -= 2;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] time = {1,3,6,8,12};  // time is sorted
        // 0,1 ->       [3]
        // <--- 0       [1]
        // 3,4 ->       [12]
        // <---- 1      [3]
        //  [1,3,6]
        CrossingRiver crossingRiver = new CrossingRiver();
        System.out.println(crossingRiver.minCost1(time, time.length));
        System.out.println(crossingRiver.minCost2(time, time.length));
    }
}
