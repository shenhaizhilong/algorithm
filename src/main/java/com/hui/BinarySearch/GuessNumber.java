package com.hui.BinarySearch;

/**
 *https://leetcode.com/problems/guess-number-higher-or-lower/description/
 * 374. Guess Number Higher or Lower
 * DescriptionHintsSubmissionsDiscussSolution
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked.
 *
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 *
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 *
 * -1 : My number is lower
 *  1 : My number is higher
 *  0 : Congrats! You got it!
 * Example:
 * n = 10, I pick 6.
 *
 * Return 6.
 *
 * @author: shenhaizhilong
 * @date: 2018/8/19 16:15
 */
public class GuessNumber {
    public  static int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low <= high)
        {
            int middle = (low + high)>>>1;
            int res = guess(middle);

            // 细节，稍微调整一下比较的顺序就可以打败90%的提交，因为相等情况很稀少，觉得部分不是小于就是大于所以把相等放在最后
            if(res <0){
                high = middle -1;
            }else if(res >0)
            {
                low = middle + 1;
            }else {
                return middle;
            }


        }

        return -1;
    }


    /**
     * 用两个枢轴，把数据划分为三个部分
     * @param n
     * @return
     */
    public static int guessNumber2(int n)
    {
        int low = 1;
        int high = n;
        while (low <= high)
        {
            int middle1 = low + (high - low)/3;
            int middle2 = high - (high - low)/3;
            int res1 = guess(middle1);
            int res2 = guess(middle2);
            if(res1 == 0)return middle1;
            if(res2 == 0)return middle2;
            if(res1 <0){
                high = middle1 -1;
            }else if(res2 > 0)
            {
                low = middle2 + 1;
            }else {
                high = middle2 -1;
                low = middle1 +1;
            }
        }
        return -1;
    }
    public static int getMoneyAmount(int n) {
        int low = 1;
        int high = n;
        int cost = 0;
        while (low <= high)
        {
            int middle = (low + high)>>>1;
            int res = guess(middle);

            // 细节，稍微调整一下比较的顺序就可以打败90%的提交，因为相等情况很稀少，觉得部分不是小于就是大于所以把相等放在最后
            if(res <0){
                high = middle -1;
                cost += middle;
            }else if(res >0)
            {
                low = middle + 1;
                cost += middle;
            }else {
                break;
            }

        }

        return cost;
    }

    public static int getMoneyAmount2(int n)
    {
        int low = 1;
        int high = n;
        int cost = 0;
        while (low <= high)
        {
            int middle1 = low + (high - low)/3;
            int middle2 = high - (high - low)/3;
            int res1 = guess(middle1);
            int res2 = guess(middle2);
            if(res1 == 0)break;
            if(res2 == 0)break;
            if(res1 <0){
                high = middle1 -1;
                cost += middle1;
            }else if(res2 > 0)
            {
                low = middle2 + 1;
                cost += middle2;
            }else {
                high = middle2 -1;
                low = middle1 +1;
                cost += middle1 + middle2;
            }
        }
        return cost;
    }


    public static int guess(int number)
    {
        return 100 - number;
    }

    public static void main(String[] args) {

        int ans = 8;
        int number = 100000;
        System.out.println(getMoneyAmount(number));
        System.out.println(getMoneyAmount2(number));

    }

}
