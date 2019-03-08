package com.hui.DynamicPrograming;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/16 14:58
 *
 *
 * 813. Largest Sum of Averages
 * DescriptionHintsSubmissionsDiscussSolution
 * We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the largest score we can achieve?
 *
 * Note that our partition must use every number in A, and that scores are not necessarily integers.
 *
 * Example:
 * Input:
 * A = [9,1,2,3,9]
 * K = 3
 * Output: 20
 * Explanation:
 * The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
 * We could have also partitioned A into [9, 1], [2], [3, 9], for example.
 * That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 100.
 * 1 <= A[i] <= 10000.
 * 1 <= K <= A.length.
 * Answers within 10^-6 of the correct answer will be accepted as correct.
 *
 *
 */
public class LargestSumofAverages {

    public double largestSumOfAverages(int[] A, int K) {
        int N = A.length;
        double[][] memo = new double[N +1][K +1];
        double curr = 0;
        for (int i = 1; i <memo.length; i++) {
            curr += A[i -1];
            memo[i][1] = curr/i;
        }

        return dfs(N, K, A, memo);

    }

    private double dfs(int N, int k, int[] A, double[][] memo)
    {
        if(memo[N][k] != 0)return memo[N][k];
        // since k <= N, so the max k is N
        if(N < k)return 0;
        double curr = 0;
        for (int i = N -1; i >= k -1; i--) {
            curr += A[i];
            memo[N][k] = Math.max(memo[N][k], curr/(N - i) + dfs(i, k -1, A, memo));
        }
        return memo[N][k];

    }


    public double largestSumOfAverages2(int[] A, int K) {
        int N = A.length;

        // presum[i] = sum(A[0, i -1])
        // presum[j] = sum(A[0,j -1]), so presum[j] - presumm[i] = sum(A[i,j-1]), the length of A[i,j-1] is j -1 - i +1 = j -i;
        // dp[N][K] means the largest Sum of Averages ending with A[N -1] at most k groups.
        double[] presum = new double[N +1];
        double[][] dp = new double[N +1][K +1];
        for (int i = 1; i <= A.length; i++) {
            presum[i] = presum[i -1] + A[i -1];
            dp[i][1] = presum[i]/i;
        }

        for (int k = 2; k <= K; k++) {
            for (int i = k; i <= N; i++) {
                double max = Double.MIN_VALUE;
                for (int j = 0; j < i ; j++) {
                    // last groups average + prev k -1 groups value.
                    // for an example :

                    // arr:     9 , 1,  2,  3,  9
                    //preSum:0, 9, 10, 12, 15, 24
                    //  last group with ending withe A[2] maybe (9,1,2), (1,2), (2)
                    // the average is                           4         1.5    2
                    //                                         4 = (prevSum(3) - prevSum(0))/(3 -0), 1.5 = (prevSum(3) - prevSum(1))/(3 -1)
                     double sum =  (presum[i] - presum[j])/(i-j) + dp[j][k -1];
                     max = Math.max(sum, max);
                }
                dp[i][k] = max;
            }
        }

        return dp[N][K];

    }




    public static void main(String[] args) {

        LargestSumofAverages largestSumofAverages = new LargestSumofAverages();
        System.out.println(largestSumofAverages.largestSumOfAverages( new int[]{9,1,2,3,9}, 3));
        System.out.println(largestSumofAverages.largestSumOfAverages2( new int[]{9,1,2,3,9}, 3));

    }


}
