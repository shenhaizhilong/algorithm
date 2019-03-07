package com.hui.algorithm;

import java.util.*;

/**
 *
 * 187. Repeated DNA Sequences
 * DescriptionHintsSubmissionsDiscussSolution
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 *
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 *
 * Example:
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 *
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 *
 * @author: shenhaizhilong
 * @date: 2018/9/18 21:29
 */
public class RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.length() < 10) {
            return new ArrayList<>();
        }
        Set<String> set = new HashSet<>();
        Set<String> result = new HashSet<>();
        for (int i = 0; i < s.length() - 9; i++) {
            String word = s.substring(i,i + 10);
            if(!set.add(word))  // set already have word
                result.add(word); // unique
        }

        return new ArrayList<>(result);
    }

    public List<String> findRepeatedDnaSequences2(String s) {
        if (s == null || s.length() < 10) {
            return new ArrayList<>();
        }

        List<String> list = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        int hash = 0;
        char[] val = s.toCharArray();

        for (int i = 0; i < 9; i++) {
            hash = hash << 3 | val[i]&7;
        }
        for (int i = 9; i < val.length; i++) {
            hash = (hash << 3) & 0x3fffffff | val[i]&7;
            map.put(hash, map.getOrDefault(hash,0) + 1);
            if(map.get(hash) == 2)list.add(String.valueOf(val,i-9, 10));

        }

        return list;

    }

    public static void main(String[] args) {
        RepeatedDNASequences repeatedDNASequences = new RepeatedDNASequences();
        System.out.println(repeatedDNASequences.findRepeatedDnaSequences2("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }

}
