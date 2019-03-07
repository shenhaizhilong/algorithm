package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/9 22:58
 *
 *
 * 526. Beautiful Arrangement
 * DescriptionHintsSubmissionsDiscussSolution
 * Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:
 *
 * The number at the ith position is divisible by i.
 * i is divisible by the number at the ith position.
 * Now given N, how many beautiful arrangements can you construct?
 *
 * Example 1:
 * Input: 2
 * Output: 2
 * Explanation:
 *
 * The first beautiful arrangement is [1, 2]:
 *
 * Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
 *
 * Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
 *
 * The second beautiful arrangement is [2, 1]:
 *
 * Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
 *
 * Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 * Note:
 * N is a positive integer and will not exceed 15.
 *
 */
public class BeautifulArrangement {

    public int countArrangement(int N) {
        if( N < 1)return 0;
        if(N == 1)return 1;
        int[] count = {0};
        boolean[] visited = new boolean[N +1];
        backTracking(1, count, visited);
        return count[0];
    }

    private void backTracking(int idx, int[] count, boolean[] visited)
    {
        if( idx > visited.length -1)
        {
            count[0]++;
            return;
        }
        for (int j = 1; j < visited.length; j++) {
            if(!visited[j] && ( idx % j == 0 || j % idx == 0))
            {
                visited[j] = true;
                backTracking(idx +1, count, visited);
                visited[j] = false;
            }
        }

    }

    public int countArrangement2(int N) {
        if (N < 1) return 0;
        if (N == 1) return 1;
        int[] arr = new int[N];

        // first beautifulArrangement
        for (int i = 0; i < N; i++) {
            arr[i] = i +1;
        }

        return dfs(N, arr);
    }

    private int dfs(int N, int[] arr)
    {
        if(N <= 1)
        {
            //System.out.println(Arrays.toString(arr));
            return 1;
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            if(N % arr[i] == 0 || arr[i] % N == 0)
            {
                swap(arr, i, N -1);
                res += dfs(N -1, arr);
                swap(arr,i, N-1);
            }
        }
        return res;
    }

    private void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        BeautifulArrangement beautifulArrangement = new BeautifulArrangement();
//        System.out.println(beautifulArrangement.countArrangement(2));
//        System.out.println(beautifulArrangement.countArrangement(3));

        System.out.println("*****************");

   //     System.out.println(beautifulArrangement.countArrangement2(2));
        System.out.println(beautifulArrangement.countArrangement2(4));
    }

}
