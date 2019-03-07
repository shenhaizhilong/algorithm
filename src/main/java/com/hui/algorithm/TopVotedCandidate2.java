package com.hui.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/14 11:42
 *
 * 911. Online Election
 * DescriptionHintsSubmissionsDiscussSolution
 * In an election, the i-th vote was cast for persons[i] at time times[i].
 *
 * Now, we would like to implement the following query function: TopVotedCandidate.q(int t) will return the number of the person that was leading the election at time t.
 *
 * Votes cast at time t will count towards our query.  In the case of a tie, the most recent vote (among tied candidates) wins.
 *
 *
 *
 * Example 1:
 *
 * Input: ["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
 * Output: [null,0,1,1,0,0,1]
 * Explanation:
 * At time 3, the votes are [0], and 0 is leading.
 * At time 12, the votes are [0,1,1], and 1 is leading.
 * At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
 * This continues for 3 more queries at time 15, 24, and 8.
 *
 *
 * Note:
 *
 * 1 <= persons.length = times.length <= 5000
 * 0 <= persons[i] <= persons.length
 * times is a strictly increasing array with all elements in [0, 10^9].
 * TopVotedCandidate.q is called at most 10000 times per test case.
 * TopVotedCandidate.q(int t) is always called with t >= times[0].
 *
 */
public class TopVotedCandidate2 {

    private int[] times;
    private int[] candidate;

    public TopVotedCandidate2(int[] persons, int[] times) {
        int lead = -1;
        Map<Integer,Integer> counter = new HashMap<>();
        this.times = times;
        this.candidate = new int[persons.length];

        for (int i = 0; i < persons.length; i++) {
            //counter.merge(persons[i], 1, Integer::sum);
            int count =  counter.getOrDefault(persons[i],0) +1;
            counter.put(persons[i], count);
            if(i == 0 || count >= counter.get(lead))lead = persons[i];
            candidate[i] = lead;
        }


    }

    public int q(int t) {

        int idx = Arrays.binarySearch(times, t);
        return idx < 0 ? candidate[-idx -2]: candidate[idx];
    }
}
