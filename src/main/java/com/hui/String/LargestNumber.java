package com.hui.String;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 *https://leetcode-cn.com/problems/largest-number/description/
 * 对于两个数字a和b来说，如果将其都转为字符串，如果ab > ba，则a排在前面，比如9和34，由于934>349，
 * 所以9排在前面，再比如说30和3，由于303<330，所以3排在30的前面。按照这种规则对原数组进行排序后，将每个数字转化为字符串再连接起来就是最终结果。
 *
 * @author: shenhaizhilong
 * @date: 2018/7/1 0:12
 */
public class LargestNumber {
    public static String largestNumber(int[] nums) {

        if(nums == null) throw new IllegalArgumentException("nums can't be null");
        if(nums.length == 0)return "";
        if(nums.length == 1)return Integer.toString(nums[0]);

        String[] arrays = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arrays[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(arrays, (a,b) -> (a +b).compareTo(b +a));
        StringBuilder sb = new StringBuilder();
        for (int i = arrays.length -1; i >=0; i--) {
            sb.append(arrays[i]);
        }
        if(sb.charAt(0)=='0'){
            return "0";
        }
        return sb.toString();



    }


    public static String largestNumber3(int[] nums) {

        String[] arrays = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arrays[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(arrays, (a,b) -> (a +b).compareTo(b +a));
        StringBuilder sb = new StringBuilder();
        for (int i = arrays.length -1; i >=0; i--) {
            sb.append(arrays[i]);
        }
        if(sb.charAt(0)=='0'){
            return "0";
        }
        return sb.toString();



    }    /**
     *
     * 执行用时为 20 ms 的范例
     * 但是经过我测试这种方法比我的方法用时很多
     * @param nums
     * @return
     */
    public static String largestNumber2(int[] nums) {
        String[] strNums = new String[nums.length];
        for(int i=0;i<nums.length;i++){
            strNums[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strNums,new Comparator<String>(){
            @Override
            public int compare(String s1,String s2){
                if((s1+s2).compareTo(s2+s1)>0){
                    return -1;
                }else if((s1+s2).compareTo(s2+s1)<0){
                    return 1;
                }else if((s1+s2).compareTo(s2+s1)==0){
                    return 0;
                }
                return 0;
            }
        });
        StringBuilder res = new StringBuilder();
        for(String i:strNums){
            res.append(i);
        }
        if(res.charAt(0)=='0'){
            return "0";
        }
        return res.toString();
    }

    public static void main(String[] args) {

//        int[] a = {3, 30, 34, 5, 9};
//        System.out.println("Original :" + Arrays.toString(a));
//        String res = largestNumber(a);
//        System.out.println(res);
//
//        System.out.println(largestNumber(new int[]{0,0}));
        for (int j = 0; j < 10; j++) {
            int count = 500000;
            long t1 = System.nanoTime();
            for (int i = 0; i < count; i++) {
                largestNumber(new int[] {3,30,34,5,9,0,10,100});
            }
            long t2 = System.nanoTime();

            for (int i = 0; i < count; i++) {
                largestNumber2(new int[] {3,30,34,5,9,0, 10,100});
            }

            long t3 = System.nanoTime();

            for (int i = 0; i < count; i++) {
                largestNumber3(new int[] {3,30,34,5,9,0,10,100});
            }
            long t4 = System.nanoTime();

            System.out.println((t2 -t1));
            System.out.println((t3 -t2));
            System.out.println((t4-t3));
            System.out.println("******");

        }


    }

}
