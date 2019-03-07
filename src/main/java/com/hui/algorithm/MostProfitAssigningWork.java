package com.hui.algorithm;

import java.util.*;



/**
 * @author: shenhaizhilong
 * @date: 2018/11/10 0:02
 *
 *
 * 826. Most Profit Assigning Work
 * DescriptionHintsSubmissionsDiscussSolution
 * We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job.
 *
 * Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with difficulty at most worker[i].
 *
 * Every worker can be assigned at most one job, but one job can be completed multiple times.
 *
 * For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, his profit is $0.
 *
 * What is the most profit we can make?
 *
 * Example 1:
 *
 * Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 * Output: 100
 * Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] seperately.
 * Notes:
 *
 * 1 <= difficulty.length = profit.length <= 10000
 * 1 <= worker.length <= 10000
 * difficulty[i], profit[i], worker[i]  are in range [1, 10^5]
 *
 *
 */
public class MostProfitAssigningWork {

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int N = difficulty.length;
        List<int[]> list = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            list.add(new int[]{difficulty[i], profit[i]}) ;
        }

       Collections.sort(list, new Comparator<int[]>() {
           @Override
           public int compare(int[] o1, int[] o2) {
               return o1[0] - o2[0];
           }
       });

        Arrays.sort(worker);
        int ans = 0;
        int idx = 0;
        int max = 0;
        for(int abitity: worker)
        {

           while (idx < N && abitity >= list.get(idx)[0])
           {
               max = Math.max(max, list.get(idx)[1]);
               idx++;
           }
           ans += max;

        }

        return ans;

    }

    private void print(List<int[]> list)
    {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(Arrays.toString(list.get(i)));
        }
    }

    public int maxProfitAssignment2(int[] difficulty, int[] profit, int[] worker) {
        int max = 0;
        for (int i = 0; i < difficulty.length  ; i++) {
            max = Math.max(max, difficulty[i]);
        }

        int[] map = new int[max+1];
        for (int i = 0; i < difficulty.length ; i++) {
            map[difficulty[i]] = Math.max(profit[i], map[difficulty[i]]);
        }


        for (int i = 1; i < map.length; i++) {
            map[i] = Math.max(map[i-1], map[i]);
        }

        int ans = 0;
        for(int ability: worker)
        {
            if(ability > max)
            {
                ans += map[map.length -1];
            }else {
                ans += map[ability];
            }
        }

        return ans;

    }

    public static void main(String[] args) {


        MostProfitAssigningWork mostProfitAssigningWork = new MostProfitAssigningWork();
//        int[] difficulty = {2,4,6,8,10}, profit = {10,20,30,40,50};
//        int[] worker = {4,5,6,7};

//        int[] difficulty =  {68,35,52,47,86};
//        int[] profit = {67,17,1,81,3};
//        int[] worker = {92,10,85,84,82};

        int[] difficulty = {23,30,35,35,43,46,47,81,83,98};
        int[] profit = {8,11,11,20,33,37,60,72,87,95};
        int[] worker = {95,46,47,97,11,35,99,56,41,92};

        System.out.println(mostProfitAssigningWork.maxProfitAssignment(difficulty,profit, worker));
        System.out.println(mostProfitAssigningWork.maxProfitAssignment2(difficulty,profit, worker));
    }
}
