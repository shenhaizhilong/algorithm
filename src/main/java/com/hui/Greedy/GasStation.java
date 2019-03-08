package com.hui.Greedy;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/21 14:43
 *
 *134. Gas Station
 * DescriptionHintsSubmissionsDiscussSolution
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 *
 * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
 *
 * Note:
 *
 * If there exists a solution, it is guaranteed to be unique.
 * Both input arrays are non-empty and have the same length.
 * Each element in the input arrays is a non-negative integer.
 * Example 1:
 *
 * Input:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * Output: 3
 *
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 * Example 2:
 *
 * Input:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 *
 * Output: -1
 *
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 *
 */
public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int N = gas.length;
        int sumFromStart = 0;  // sum all item from the start point.
        int totalSum = 0; // all the negative item from left side.

        for (int i = start; i < N; i++) {
            sumFromStart += gas[i] - cost[i];
            // if sum from the start point is negative, the start point can't be this point.
            if(sumFromStart < 0)
            {
                totalSum += sumFromStart;
                // the sum from start to i is negative, to the   next position is i +1
                start = i +1;
                sumFromStart = 0;

            }
        }


        totalSum += sumFromStart;

        //return -1 if totalSum is negative
        return totalSum < 0 ? -1:start;
    }

    public static void main(String[] args) {

        GasStation gasStation = new GasStation();
      //  System.out.println(gasStation.canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
        System.out.println(gasStation.canCompleteCircuit(new int[]{5,1,2,3,4}, new int[]{4,4,1,5,1}));
    }

}
