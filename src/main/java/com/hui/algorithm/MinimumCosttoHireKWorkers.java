package com.hui.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/8 16:08
 *
 *
 * 857. Minimum Cost to Hire K Workers
 * DescriptionHintsSubmissionsDiscussSolution
 * There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
 *
 * Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:
 *
 * Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 * Every worker in the paid group must be paid at least their minimum wage expectation.
 * Return the least amount of money needed to form a paid group satisfying the above conditions.
 *
 *
 *
 * Example 1:
 *
 * Input: quality = [10,20,5], wage = [70,50,30], K = 2
 * Output: 105.00000
 * Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
 * Example 2:
 *
 * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
 * Output: 30.66667
 * Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
 *
 *
 * Note:
 *
 * 1 <= K <= N <= 10000, where N = quality.length = wage.length
 * 1 <= quality[i] <= 10000
 * 1 <= wage[i] <= 10000
 * Answers within 10^-5 of the correct answer will be considered correct.
 *
 *
 * https://leetcode.com/problems/minimum-cost-to-hire-k-workers/discuss/141768/Detailed-explanation-O(NlogN)
 *
 *
 */
public class MinimumCosttoHireKWorkers {

    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        double[][] workers = new double[quality.length][2];
        for (int i = 0; i < quality.length; i++) {
            workers[i][0] = (double)wage[i]/quality[i];
            workers[i][1] = quality[i];
        }

        // sort by ratio
        Arrays.sort(workers, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
               return Double.compare(o1[0], o2[0]);
            }
        });

        PriorityQueue<Double> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        double qualitySum = 0;
        double ans = Double.MAX_VALUE;
        for(double[] worker: workers)
        {
            qualitySum += worker[1];  // the quality Sum , total wages = qualitySum*ratio;
            priorityQueue.add(worker[1]);
            if(priorityQueue.size() > K){
                qualitySum -= priorityQueue.poll();  // remove the max quality, since ratio is monotone increasing, to let totalWages is the
                // the min, so we need remove the max quality from paid group,which group size is k +1
            }
            if(priorityQueue.size() == K)
            {
                ans = Math.min(ans, qualitySum*worker[0]);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        MinimumCosttoHireKWorkers minimumCosttoHireKWorkers = new MinimumCosttoHireKWorkers();
        System.out.println(minimumCosttoHireKWorkers.mincostToHireWorkers(new int[]{10,20,5},new int[]{70,50,30},2));
    }

}
