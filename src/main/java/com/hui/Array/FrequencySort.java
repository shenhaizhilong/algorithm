package com.hui.Array;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/1 12:22
 */
public class FrequencySort {
    private static class ValueType
    {
        private final int key;
        private final int value;
        public ValueType(int key, int value)
        {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public int compare(ValueType other)
        {
            return this.value - other.value;
        }


    }
    public static String frequencySort(String s) {
        char[] charsFreq = new char[128];
        ValueType[] valueTypes = new ValueType[128];
        for (int i = 0; i < s.length(); i++) {
            charsFreq[s.charAt(i)] += 1;
        }
        for (int i = 0; i < charsFreq.length; i++) {
            valueTypes[i] = new ValueType(i, charsFreq[i]);
        }

        Arrays.sort(valueTypes, (a,b) -> a.compare(b));
        StringBuilder sb = new StringBuilder();
        int freq;
        for (int i = valueTypes.length -1; i >=0 ; i--) {
            freq = valueTypes[i].getValue();
            while (freq !=0)
            {
                sb.append((char)valueTypes[i].getKey());
                freq--;
            }

        }

        return sb.toString();
    }


    public static String frequencySort2(String s) {
        int len=s.length();
        HashMap<Character,Integer> map=new HashMap<>();
        for(int i=0;i<len;i++){
            if(map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), map.get(s.charAt(i)) +1);
            }
            else
                map.put(s.charAt(i),1);
        }
        List<Map.Entry<Character,Integer>> list=new ArrayList<>(map.entrySet());

        Collections.sort(list,(a,b) -> -1*(a.getValue() - b.getValue()));
        StringBuilder res=new StringBuilder();
        for(int i=0;i<list.size();i++){
            for(int j=0;j<list.get(i).getValue();j++){
                res.append(list.get(i).getKey());
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
        System.out.println(frequencySort("cccaaa"));
        System.out.println(frequencySort("Aabb"));


        System.out.println(frequencySort2("tree"));
        System.out.println(frequencySort2("cccaaa"));
        System.out.println(frequencySort2("Aabb"));
    }
}
