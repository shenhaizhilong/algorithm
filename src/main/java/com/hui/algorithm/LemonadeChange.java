package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/28 15:35
 */
public class LemonadeChange {

    /**
     *
     *
     * https://leetcode.com/problems/lemonade-change/
     * 860. Lemonade Change
     * DescriptionHintsSubmissionsDiscussSolution
     * At a lemonade stand, each lemonade costs $5.
     *
     * Customers are standing in a queue to buy from you, and order one at a time (in the order specified by bills).
     *
     * Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.  You must provide the correct change to each customer, so that the net transaction is that the customer pays $5.
     *
     * Note that you don't have any change in hand at first.
     *
     * Return true if and only if you can provide every customer with correct change.
     *
     *
     *
     * Example 1:
     *
     * Input: [5,5,5,10,20]
     * Output: true
     * Explanation:
     * From the first 3 customers, we collect three $5 bills in order.
     * From the fourth customer, we collect a $10 bill and give back a $5.
     * From the fifth customer, we give a $10 bill and a $5 bill.
     * Since all customers got correct change, we output true.
     * Example 2:
     *
     * Input: [5,5,10]
     * Output: true
     * Example 3:
     *
     * Input: [10,10]
     * Output: false
     * Example 4:
     *
     * Input: [5,5,10,10,20]
     * Output: false
     * Explanation:
     * From the first two customers in order, we collect two $5 bills.
     * For the next two customers in order, we collect a $10 bill and give back a $5 bill.
     * For the last customer, we can't give change of $15 back because we only have two $10 bills.
     * Since not every customer received correct change, the answer is false.
     *
     *
     * Note:
     *
     * 0 <= bills.length <= 10000
     * bills[i] will be either 5, 10, or 20.
     *
     * [5,5]  1
     * [5,10]   1
     * [5,10,5,10] 1
     * [10,5]  0
     * [20,5] 0
     * [5,20] 0
     * [
     * @param bills
     * @return
     */
    public static boolean lemonadeChange(int[] bills) {
        if(bills == null || bills.length == 0)return true;
        if(bills[0] == 10 || bills[0] == 20)return false;
        int[] counter = new int[21];
        for (int i = 0; i < bills.length; i++) {
           int b = bills[i];
           if(b == 5)
           {
               counter[5]++;
           }else if(b == 10)
           {
               counter[10]++;
               counter[5]--;
               if(counter[5] < 0)return false;
           }else if(b == 20)
           {
               counter[b]++;
               if(counter[10] -1 >=0 && counter[5] -1 >=0)
               {
                   counter[10]--;
                   counter[5]--;
               }else if(counter[5] -3>=0)
               {
                   counter[5] -= 3;
               }else {
                   return false;
               }
           }
        }

        return true;
    }


    public static void main(String[] args) {
        int[] bills0 = {5,5,10,10,20};
        int[] bills1 = {5};
        int[] bills2 = {5,10};
        int[] bills3 = {5,10,5};
        int[] bills4 = {10,5,5};
        int[] bills5 = {20,5,5,10};

        System.out.println(lemonadeChange(bills0));
        System.out.println(lemonadeChange(bills1));
        System.out.println(lemonadeChange(bills2));
        System.out.println(lemonadeChange(bills3));
        System.out.println(lemonadeChange(bills4));
        System.out.println(lemonadeChange(bills5));

    }
}
