package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/24 11:43
 *
 *
 * 781. Rabbits in Forest
 * DescriptionHintsSubmissionsDiscussSolution
 * In a forest, each rabbit has some color. Some subset of rabbits (possibly all of them) tell you how many other rabbits have the same color as them. Those answers are placed in an array.
 *
 * Return the minimum number of rabbits that could be in the forest.
 *
 * Examples:
 * Input: answers = [1, 1, 2]
 * Output: 5
 * Explanation:
 * The two rabbits that answered "1" could both be the same color, say red.
 * The rabbit than answered "2" can't be red or the answers would be inconsistent.
 * Say the rabbit that answered "2" was blue.
 * Then there should be 2 other blue rabbits in the forest that didn't answer into the array.
 * The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn't.
 *
 * Input: answers = [10, 10, 10]
 * Output: 11
 *
 * Input: answers = []
 * Output: 0
 * Note:
 *
 * answers will have length at most 1000.
 * Each answers[i] will be an integer in the range [0, 999].
 *
 *
 */
public class RabbitsinForest {

    public int numRabbits(int[] answers) {
        if(answers == null || answers.length < 1)return 0;
        int[] map = new int[1000];
        int answer = 0;
        for(int n: answers)
        {
            if(n == 0)
            {
                // no other same color rabbits just add 1
                answer++;
                continue;
            }
            map[n]++;
             if(map[n] == n +1)
             {
                 answer += n +1;
                 map[n] = 0;
             }

        }

        for (int i = 0; i < map.length; i++) {
           if(map[i] > 0)
           {
               answer += i +1;
           }
        }
        return answer;
    }

    public int numRabbits2(int[] answers) {
        if(answers == null || answers.length < 1)return 0;
        int[] map = new int[1000];
        int answer = 0;
        for(int n: answers)
        {
            map[n]++;
            if(map[n] == n +1)
            {
                answer += n +1;
                map[n] = 0;
            }

        }

        for (int i = 0; i < map.length; i++) {
            if(map[i] > 0)
            {
                answer += i +1;
            }
        }
        return answer;
    }

    public int numRabbits3(int[] answers) {
        if(answers == null || answers.length < 1)return 0;
        int[] map = new int[1000];
        int answer = 0;
        for(int n: answers)
        {
            map[n]++;
            if(map[n] == 1)
            {
                answer += n +1;
            }
            if(map[n] == n +1)
            {
                map[n] = 0;
            }

        }

        return answer;
    }



    public static void main(String[] args) {
        RabbitsinForest rabbitsinForest = new RabbitsinForest();
        System.out.println(rabbitsinForest.numRabbits(new int[]{1,1,2}));
        System.out.println(rabbitsinForest.numRabbits(new int[]{}));
        System.out.println(rabbitsinForest.numRabbits(new int[]{10,10,10}));
        System.out.println(rabbitsinForest.numRabbits(new int[]{0,0,1,1,1}));

        System.out.println("***********************");

        System.out.println(rabbitsinForest.numRabbits2(new int[]{1,1,2}));
        System.out.println(rabbitsinForest.numRabbits2(new int[]{}));
        System.out.println(rabbitsinForest.numRabbits2(new int[]{10,10,10}));
        System.out.println(rabbitsinForest.numRabbits2(new int[]{0,0,1,1,1}));
        System.out.println("***********************");
        System.out.println(rabbitsinForest.numRabbits3(new int[]{1,1,2}));
        System.out.println(rabbitsinForest.numRabbits3(new int[]{}));
        System.out.println(rabbitsinForest.numRabbits3(new int[]{10,10,10}));
        System.out.println(rabbitsinForest.numRabbits3(new int[]{0,0,1,1,1}));

    }
}
